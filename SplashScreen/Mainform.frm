VERSION 5.00
Object = "{248DD890-BB45-11CF-9ABC-0080C7E7B78D}#1.0#0"; "MSWINSCK.OCX"
Object = "{67397AA1-7FB1-11D0-B148-00A0C922E820}#6.0#0"; "msadodc.ocx"
Begin VB.Form MainForm 
   BorderStyle     =   0  'None
   Caption         =   "SplashScreen"
   ClientHeight    =   7230
   ClientLeft      =   0
   ClientTop       =   0
   ClientWidth     =   11025
   LinkTopic       =   "Form1"
   Picture         =   "Mainform.frx":0000
   ScaleHeight     =   7230
   ScaleWidth      =   11025
   ShowInTaskbar   =   0   'False
   StartUpPosition =   2  'CenterScreen
   Begin MSAdodcLib.Adodc Adodc 
      Height          =   330
      Left            =   1680
      Top             =   240
      Visible         =   0   'False
      Width           =   1200
      _ExtentX        =   2117
      _ExtentY        =   582
      ConnectMode     =   0
      CursorLocation  =   3
      IsolationLevel  =   -1
      ConnectionTimeout=   15
      CommandTimeout  =   30
      CursorType      =   3
      LockType        =   3
      CommandType     =   8
      CursorOptions   =   0
      CacheSize       =   50
      MaxRecords      =   0
      BOFAction       =   0
      EOFAction       =   0
      ConnectStringType=   1
      Appearance      =   1
      BackColor       =   -2147483643
      ForeColor       =   -2147483640
      Orientation     =   0
      Enabled         =   -1
      Connect         =   ""
      OLEDBString     =   ""
      OLEDBFile       =   ""
      DataSourceName  =   ""
      OtherAttributes =   ""
      UserName        =   ""
      Password        =   ""
      RecordSource    =   ""
      Caption         =   "Adodc1"
      BeginProperty Font {0BE35203-8F91-11CE-9DE3-00AA004BB851} 
         Name            =   "MS Sans Serif"
         Size            =   8.25
         Charset         =   0
         Weight          =   400
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      _Version        =   393216
   End
   Begin VB.Timer Timer1 
      Left            =   480
      Top             =   120
   End
   Begin MSWinsockLib.Winsock Tcp 
      Left            =   0
      Top             =   120
      _ExtentX        =   741
      _ExtentY        =   741
      _Version        =   393216
   End
   Begin MSWinsockLib.Winsock TcpClient 
      Left            =   960
      Top             =   120
      _ExtentX        =   741
      _ExtentY        =   741
      _Version        =   393216
      RemoteHost      =   ""
   End
   Begin VB.Label Lbl_LoadInfo 
      BackColor       =   &H80000018&
      BackStyle       =   0  'Transparent
      Caption         =   "Label1"
      Height          =   615
      Left            =   600
      TabIndex        =   0
      Top             =   6600
      Width           =   9855
   End
End
Attribute VB_Name = "MainForm"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
Option Explicit

'声明
Private Declare Function timeGetTime Lib "winmm.dll" () As Long
Private Declare Sub Sleep Lib "kernel32" (ByVal dwMilliseconds As Long)
Private Declare Function GetVolumeInformation Lib "kernel32.dll" Alias _
    "GetVolumeInformationA" (ByVal lpRootPathName As String, _
    ByVal lpVolumeNameBuffer As String, ByVal nVolumeNameSize As Integer, _
    lpVolumeSerialNumber As Long, lpMaximumComponentLength As Long, _
    lpFileSystemFlags As Long, ByVal lpFileSystemNameBuffer As String, _
    ByVal nFileSystemNameSize As Long) As Long

'定义
Public Str_Cpuid, Str_Diskid, Str_MachineSN As String
Public SQL_Server, SQL_DB, SQL_User, SQL_Pwd, SQL_Port As String
Dim Num_TimeSelect As Long

'获取网络时间日期
Private Function strGetDate() As String
    Dim XmlHttp As Object
    Set XmlHttp = CreateObject("Microsoft.XMLHTTP")
    'XmlHttp.Open "Get", "http://www.symental.com/time/date.asp", False
    XmlHttp.send
    strGetDate = StrConv(XmlHttp.ResponseBody, vbUnicode)
    Set XmlHttp = Nothing
End Function

Sub GetHardwareInfo()
'WMIC BIOS list full获取bios信息
    Dim mySerial As Long
    Dim mylong As Long
    Dim a As SWbemServices
    Dim b As SWbemObjectSet
    Dim c As SWbemObject
    Dim d As SWbemPropertySet
    Dim e As SWbemProperty
    Dim mystr As String
    Dim mytype As String
    Dim sRoot As String
    
    Set a = GetObject("winmgmts:")
    Set b = a.InstancesOf("Win32_Processor")
    For Each c In b
       With c
        If .Properties_.Count > 0 Then
         Set d = .Properties_
          For Each e In d
          If e.Name = "ProcessorId" Then
               Str_Cpuid = e.Value
          End If
         Next
        End If
       End With
    Next
    mystr = String$(255, Chr$(0))
    mytype = String$(255, Chr$(0))
    sRoot = "c:\"                                     '设定获取C盘序列号
    mylong = GetVolumeInformation(sRoot, mystr, Len(mystr), mySerial, 0, 0, mytype, Len(mytype))
    mySerial = Mid(mySerial, 2)
    Str_Diskid = CStr(mySerial)
End Sub

Sub APPstart(subpath As String)
    Dim path As String
    path = App.path + subpath
    Shell path, vbMinimizedFocus
End Sub

Sub GetMachineSN()
    Dim time As String
    Dim yy As String
    Dim mmdd As String

    time = Now()
    yy = Left(time, 4)
    mmdd = Mid(time, 6, 2) + Mid(time, 9, 2)
    '''''TS20160D0ABFEBFBFF000506E30DYLJ0A241900D0A1230TY'''''
    If IsNull(Str_Cpuid) Then
        Str_Cpuid = CStr(Int(Rnd * 1000000000))
    End If
    If Str_Diskid = Null Then
        Str_Diskid = CStr(Int(Rnd * 1000000))
    End If
    Str_MachineSN = "TS" + yy + "0DT0A" + Str_Cpuid + "D0YA0" + Str_Diskid + "TSYLJ" + mmdd + "TYS"
End Sub

Sub waitms(t As Long)
    Dim Savetime As Double

    Savetime = timeGetTime
    While timeGetTime < Savetime + t
        DoEvents
    Wend
End Sub

Private Sub Form_Load()
    Num_TimeSelect = 0
    Lbl_LoadInfo.FontSize = 11
    Lbl_LoadInfo.Caption = "Initialize..."
    Timer1.Enabled = True
    Timer1.Interval = 500
    
    TcpClient.RemoteHost = "127.0.0.1"
    TcpClient.RemotePort = 31008
End Sub


Private Sub TcpClient_SendComplete()
    TcpClient.Close
    Unload Me
End Sub

Private Sub Timer1_Timer()
    Dim Index As Long
    Index = 0
    Select Case Num_TimeSelect
        Case 0  '注册信息确认
            GetHardwareInfo
            GetMachineSN
            Lbl_LoadInfo.Caption = "RegInfo Comfirm..."
            ''''''''''RegForm.Show
            On Error Resume Next
            Shell "taskkill /f /im " & "MATP.exe"
            Shell "taskkill /f /im MATPDebug.exe"
            Shell "taskkill /f /im matpwebserver.exe"
            Tcpserver
            
        Case 1 '数据库参数确认，并连接数据库
            Lbl_LoadInfo.Caption = "Connect Database..."
            waitms 200
            Timer1.Enabled = False
            
            Dim inipath As String
            inipath = App.path + "\data\config.ini"
            On Error GoTo inifile
            SQL_Server = GetPrivateStringValue("SQLSERVER", "ServerPar.Server", inipath)
            SQL_Server = Replace(SQL_Server, "\\", "\")
            SQL_DB = GetPrivateStringValue("SQLSERVER", "ServerPar.Database", inipath)
            SQL_User = GetPrivateStringValue("SQLSERVER", "ServerPar.User", inipath)
            SQL_Pwd = GetPrivateStringValue("SQLSERVER", "ServerPar.Pwd", inipath)
            SQL_Port = GetPrivateStringValue("SQLSERVER", "ServerPar.Port", inipath)
            
            If SQL_Server = "" Or SQL_DB = "" Or SQL_User = "" Or SQL_Pwd = "" Then
                Database.Show
                Exit Sub
            End If
            
            Dim SQL_ConnectStr As String
            SQL_ConnectStr = "provider=SQLOlEDB.1;persist security info= false;uid=" + SQL_User + ";pwd=" + SQL_Pwd + ";Initial Catalog=" + SQL_DB + ";Data Source=" + SQL_Server
            
            Dim sqlcon As New ADODB.Connection
            On Error GoTo sqlerr
            sqlcon.Open SQL_ConnectStr
            
            sqlcon.Close

            Timer1.Enabled = True
            
        Case 2 '启动主程序
            Lbl_LoadInfo.Caption = "MATP Loading..."
            Timer1.Enabled = False
            APPstart "\MATP.exe"
            waitms 500
        
        Case 3 '启动调试程序
            Lbl_LoadInfo.Caption = "DebugServer Loading..."
            Timer1.Enabled = False
            APPstart "\MATPDebug.exe"
            waitms 500
            TcpClient.Connect
        
        Case 4 '启动webserver
            Lbl_LoadInfo.Caption = "MatpWebServer Loading..."
            Timer1.Enabled = False
            APPstart "\matpwebserver.exe"
            Shell "taskkill /f /im MATPDebug.exe"
            
        Case 5 '显示主界面
            Lbl_LoadInfo.Caption = "MatpShow"
            TcpClient.SendData "S"
        
    End Select
    
    Num_TimeSelect = Num_TimeSelect + 1
    
    Exit Sub
    
sqlerr:
    MsgBox "Connect Database Error", vbOKOnly, "MATP"
    
inifile:
    Database.Show
        
End Sub

Sub Tcpserver()
    Tcp.LocalPort = 31007
    Tcp.Listen
End Sub

Private Sub Tcp_ConnectionRequest(ByVal requestID As Long)
    If Tcp.State <> sckClosed Then
        Tcp.Close
    End If
    Tcp.Accept requestID
End Sub

Private Sub Tcp_DataArrival(ByVal bytesTotal As Long)
    Dim res As String
    
    Tcp.GetData res
    
    Select Case res
        Case "M"
            Lbl_LoadInfo.Caption = "MATP Load Suc"
            Timer1.Enabled = True
        Case "D"
            Lbl_LoadInfo.Caption = "DebugServer Load Suc"
            Timer1.Enabled = True
        Case "W"
            Lbl_LoadInfo.Caption = "MatpWebServer Load Suc"
            Timer1.Enabled = True
        Case Else
            'Lbl_LoadInfo.Caption = res & Tcp.State

        
    End Select
End Sub

Private Sub Tcp_Close()
    Tcp.Close
    Tcp.LocalPort = 31007
    Tcp.Listen
End Sub

