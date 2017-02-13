VERSION 5.00
Begin VB.Form Database 
   BorderStyle     =   0  'None
   Caption         =   "Form1"
   ClientHeight    =   3360
   ClientLeft      =   0
   ClientTop       =   0
   ClientWidth     =   4770
   LinkTopic       =   "Form1"
   ScaleHeight     =   3360
   ScaleWidth      =   4770
   ShowInTaskbar   =   0   'False
   StartUpPosition =   2  'CenterScreen
   Begin VB.CommandButton Command1 
      Caption         =   "Connect"
      Height          =   375
      Left            =   1680
      TabIndex        =   8
      Top             =   2760
      Width           =   1455
   End
   Begin VB.TextBox Txt_Pwd 
      Height          =   375
      IMEMode         =   3  'DISABLE
      Left            =   1680
      PasswordChar    =   "*"
      TabIndex        =   7
      Text            =   "Text1"
      Top             =   2040
      Width           =   2655
   End
   Begin VB.TextBox Txt_User 
      Height          =   375
      Left            =   1680
      TabIndex        =   6
      Text            =   "Text1"
      Top             =   1440
      Width           =   2655
   End
   Begin VB.TextBox Txt_DB 
      Height          =   375
      Left            =   1680
      TabIndex        =   5
      Text            =   "Text1"
      Top             =   840
      Width           =   2655
   End
   Begin VB.TextBox Txt_Server 
      Height          =   375
      Left            =   1680
      TabIndex        =   0
      Text            =   "Text1"
      Top             =   240
      Width           =   2655
   End
   Begin VB.Label Label1 
      Caption         =   "Password"
      Height          =   375
      Index           =   3
      Left            =   480
      TabIndex        =   4
      Top             =   2160
      Width           =   855
   End
   Begin VB.Label Label1 
      Caption         =   "User"
      Height          =   375
      Index           =   2
      Left            =   480
      TabIndex        =   3
      Top             =   1560
      Width           =   855
   End
   Begin VB.Label Label1 
      Caption         =   "Database"
      Height          =   375
      Index           =   1
      Left            =   480
      TabIndex        =   2
      Top             =   960
      Width           =   855
   End
   Begin VB.Label Label1 
      Caption         =   "Server"
      Height          =   375
      Index           =   0
      Left            =   480
      TabIndex        =   1
      Top             =   360
      Width           =   855
   End
End
Attribute VB_Name = "Database"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
Private Sub Command1_Click()
    Dim inipath As String
    inipath = App.path + "\data\config.ini"

    Dim SQL_ConnectStr As String
    SQL_ConnectStr = "provider=SQLOlEDB.1;persist security info= false;uid=" + Txt_User + ";pwd=" + Txt_Pwd + ";Initial Catalog=" + Txt_DB + ";Data Source=" + Txt_Server
    
    Dim sqlcon As New ADODB.Connection
    On Error GoTo sqlerr
    sqlcon.Open SQL_ConnectStr
    
    sqlcon.Close
    MsgBox "Connect Database Suc!", vbOKOnly, "MATP Database"
    Txt_Server = Replace(Txt_Server, "\", "\\")
    WritePrivateProfileStringByKeyName& "SQLSERVER", "ServerPar.Server", Txt_Server, inipath
    WritePrivateProfileStringByKeyName& "SQLSERVER", "ServerPar.Database", Txt_DB, inipath
    WritePrivateProfileStringByKeyName& "SQLSERVER", "ServerPar.User", Txt_User, inipath
    WritePrivateProfileStringByKeyName& "SQLSERVER", "ServerPar.Pwd", Txt_Pwd, inipath
    WritePrivateProfileStringByKeyName& "SQLSERVER", "ServerPar.Port", "1433", inipath
    Unload MainForm
    Unload Me
        
    Exit Sub
sqlerr:
    MsgBox "Connect Database Error,please retry!", vbOKOnly, "MATP Database"
End Sub

Private Sub Form_Load()
    Txt_Server.Text = MainForm.SQL_Server
    Txt_DB.Text = MainForm.SQL_DB
    Txt_User.Text = MainForm.SQL_User
    Txt_Pwd.Text = MainForm.SQL_Pwd
End Sub

