VERSION 5.00
Begin VB.Form RegForm 
   BorderStyle     =   0  'None
   Caption         =   "RegForm"
   ClientHeight    =   3705
   ClientLeft      =   0
   ClientTop       =   0
   ClientWidth     =   5430
   LinkTopic       =   "Form2"
   ScaleHeight     =   3705
   ScaleWidth      =   5430
   ShowInTaskbar   =   0   'False
   StartUpPosition =   2  'CenterScreen
   Begin VB.CommandButton Btn_Exit 
      Caption         =   "Exit"
      Height          =   615
      Left            =   360
      TabIndex        =   4
      Top             =   2640
      Width           =   1815
   End
   Begin VB.CommandButton Btn_Reg 
      Caption         =   "Reg"
      Height          =   615
      Left            =   3240
      TabIndex        =   3
      Top             =   2640
      Width           =   1815
   End
   Begin VB.TextBox Txt_RegCode 
      Alignment       =   2  'Center
      Height          =   495
      Left            =   480
      MaxLength       =   23
      TabIndex        =   2
      Top             =   1920
      Width           =   4455
   End
   Begin VB.TextBox Txt_MachineSN 
      Height          =   615
      Left            =   480
      MultiLine       =   -1  'True
      TabIndex        =   1
      Text            =   "RegForm.frx":0000
      Top             =   840
      Width           =   4455
   End
   Begin VB.Label Lbl_RegCode 
      Caption         =   "×¢²áÂë£º"
      Height          =   255
      Left            =   480
      TabIndex        =   5
      Top             =   1560
      Width           =   1215
   End
   Begin VB.Label Lbl_MachineSN 
      Caption         =   "»úÆ÷Âë£º"
      Height          =   255
      Left            =   480
      TabIndex        =   0
      Top             =   480
      Width           =   1095
   End
End
Attribute VB_Name = "RegForm"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
Option Explicit

Private Sub Form_Load()
    Txt_RegCode.FontSize = 12
    Txt_RegCode.Text = "XXXXX-XXXXX-XXXXX-XXXXX"
    Txt_MachineSN.Text = MainForm.Str_MachineSN
End Sub

Private Sub Btn_Exit_Click()
    Unload Me
    Unload MainForm
End Sub

Private Sub Txt_RegCode_Click()
    If Txt_RegCode.Text = "XXXXX-XXXXX-XXXXX-XXXXX" Then
        Txt_RegCode.Text = ""
    End If
End Sub
