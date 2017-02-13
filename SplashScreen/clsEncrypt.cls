VERSION 1.0 CLASS
BEGIN
  MultiUse = -1  'True
  Persistable = 0  'NotPersistable
  DataBindingBehavior = 0  'vbNone
  DataSourceBehavior  = 0  'vbNone
  MTSTransactionMode  = 0  'NotAnMTSObject
END
Attribute VB_Name = "dsEncrypt"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = True
Attribute VB_PredeclaredId = False
Attribute VB_Exposed = False
Option Explicit
   Private LCW As Integer
   Private LS2E As Integer
   Private LAM As Integer
   Private MP As Integer
   Private Matrix As String
   Private mov1 As String
   Private mov2 As String
   Private CodeWord As String
   Private CWL As String
   Private EncryptedString As String
   Private EncryptedLetter As String
   Private strCryptMatrix(97) As String
   
Public Property Let KeyString(sKeyString As String)
    CodeWord = sKeyString
End Property

Public Function Encrypt(mstext As String) As String
    Dim X As Integer
    Dim Y As Integer
    Dim Z As Integer
    Dim C2E As String
    Dim Str2Encrypt As String

    Str2Encrypt = mstext
    LS2E = Len(mstext)
    LCW = Len(CodeWord)
    EncryptedLetter = ""
    EncryptedString = ""

    Y = 1
    For X = 1 To LS2E
        C2E = Mid(Str2Encrypt, X, 1)
        MP = InStr(1, Matrix, C2E, 0)
        CWL = Mid(CodeWord, Y, 1)
        For Z = 1 To LAM
            If Mid(strCryptMatrix(Z), MP, 1) = CWL Then
                EncryptedLetter = Left(strCryptMatrix(Z), 1)
                EncryptedString = EncryptedString + EncryptedLetter
                Exit For
            End If
        Next Z
        Y = Y + 1
        If Y > LCW Then Y = 1
    Next X
    Encrypt = EncryptedString

End Function
Private Sub Class_Initialize()

    Dim W As Integer
    Dim X As Integer
    
    Matrix = "8x3p5BeabcdfghijklmnoqrstuvwyzACDEFGHIJKLMNOPQRSTUVWXYZ 1246790-.#/\!@$<>&*()[]{}';:,?=+~`^|%_"
    Matrix = Matrix + Chr(13)
    Matrix = Matrix + Chr(10)
    Matrix = Matrix + Chr(34)

    W = 1
    LAM = Len(Matrix)
    strCryptMatrix(1) = Matrix
  
    For X = 2 To LAM
        mov1 = Left(strCryptMatrix(W), 1)
        mov2 = Right(strCryptMatrix(W), (LAM - 1))
        strCryptMatrix(X) = mov2 + mov1
        W = W + 1
    Next X
End Sub
