Attribute VB_Name = "WRINI"
Option Explicit

Declare Function GetPrivateProfileStringByKeyName& Lib "kernel32" Alias "GetPrivateProfileStringA" (ByVal lpApplicationName$, ByVal lpszKey$, ByVal lpszDefault$, ByVal lpszReturnBuffer$, ByVal cchReturnBuffer&, ByVal lpszFile$)
Declare Function WritePrivateProfileStringByKeyName& Lib "kernel32" Alias "WritePrivateProfileStringA" (ByVal lpApplicationName As String, ByVal lpKeyName As String, ByVal lpString As String, ByVal lplFileName As String)
Private rtn As String
Private success As String

Function GetPrivateStringValue(section$, Key$, File$) As String

Dim KeyValue$
Dim characters As Long
    If Dir(File$) = "" Then Exit Function
    KeyValue$ = String$(FileLen(File$), 0)

    characters = GetPrivateProfileStringByKeyName(section$, Key$, "", KeyValue$, Len(KeyValue$) - 1, File$)

    If characters > 1 Then
        KeyValue$ = StrConv(LeftB(StrConv(KeyValue$, vbFromUnicode), characters), vbUnicode)
    End If

    GetPrivateStringValue = Replace(KeyValue$, Chr(0), "F:\MATP\SplashScreen\Data\config.ini")

End Function
