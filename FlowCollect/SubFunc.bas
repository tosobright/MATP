Type=StaticCode
Version=6
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
'Code module
'Subs in this code module will be accessible from all modules.
Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.
	Dim MediaPlayer As MediaPlayer
	Dim Bol_Play As Boolean
	MediaPlayer.Initialize'初始化
End Sub

Sub Waitms(milisecs As Int)
	Dim ti As Long   
   'Return
   ti = DateTime.Now + (milisecs)
   Do While DateTime.Now < ti
      DoEvents
   Loop   
   Return
End Sub

Sub InputEdt(edt As EditText,hint As String,inputype As String)
	Dim IME1 As IME

	edt.Color = Colors.LightGray
	edt.TextColor = Colors.Black
	edt.Hint = hint
	edt.SingleLine = True
	Select inputype
		Case "int"
			edt.InputType =edt.INPUT_TYPE_NUMBERS
		Case "phone"
			edt.InputType =edt.INPUT_TYPE_PHONE
		Case "float"
			edt.InputType =edt.INPUT_TYPE_DECIMAL_NUMBERS
		Case "IP"
			IME1.SetCustomFilter(edt, edt.INPUT_TYPE_TEXT, "0123456789.")

		Case Else
			edt.InputType =edt.INPUT_TYPE_TEXT
	End Select
End Sub

Sub Lbl_title(ui As Activity,Lbl As Label,text As String)
	Lbl.RemoveView
	ui.AddView(Lbl,20%X,2%Y,60%X,15%y)
	Lbl_AutoSize(Lbl,0.8)
	Lbl.Color = Colors.Transparent
	Lbl.TextColor = Colors.Black
	Lbl.Gravity = Gravity.CENTER
	If text <> "" Then
		Lbl.Text = text
	End If
End Sub

Sub convCSV(csv As String,Arr(,) As String)
    Dim su As StringUtils
    Dim list1 As List
	list1 = su.LoadCSV(File.DirAssets, csv, ",")
    For i = 0 To list1.Size-1
	    Dim sCol() As String 
	    sCol = list1.Get(i)
	    For j = 0 To sCol.Length-1
			Arr(i,j) =  sCol(j)
	    Next
    Next  
End Sub

Sub PlayMP3(FileName As String,PlayLoop As Boolean)
	If MediaPlayer.IsPlaying = True Then Return
	MediaPlayer.Load(File.DirAssets,FileName)
	MediaPlayer.Play'播放
	If(PlayLoop = True) Then
		MediaPlayer.Looping=True'True:播放完自动重新开始 False:播放完停下
	End If
	Bol_Play = True
End Sub

Sub StopMp3()
	If Bol_Play = True Then	MediaPlayer.Stop
	Bol_Play = False
End Sub

Sub fNumToStr(n As Float) As String
	Dim s As String
	s = n
	Return s
End Sub

Sub Edt_AutoSize(view As EditText,factor As Float) As Float
	Dim su As StringUtils
	Dim size As Float
	view.Text = "沙TOSO"
	For size = 2 To 300
		view.TextSize = size
		If su.MeasureMultilineTextHeight(view,view.Text) > view.Height Then Exit
	Next
	view.TextSize = (size-0.5) * factor	
	Return	(size-0.5) * factor
End Sub

Sub Lbl_AutoSize(view As Label,factor As Float) As Float
	Dim su As StringUtils
	Dim size As Float
	view.Text = "HMI"
	For size = 2 To 300
		view.TextSize = size
		If su.MeasureMultilineTextHeight(view,view.Text) > view.Height Then Exit
	Next
	view.TextSize = (size-0.5) * factor
	Return	(size-0.5) * factor
End Sub

Sub Btn_AutoSize(view As Button,factor As Float) As Float
	Dim su As StringUtils
	Dim size As Float
	view.Text = "沙TOSO"
	For size = 2 To 300
		view.TextSize = size
		If su.MeasureMultilineTextHeight(view,view.Text) > view.Height Then Exit
	Next
	view.TextSize = (size-0.5) * factor	
	Return	(size-0.5) * factor
End Sub

Sub ViewCtl(ui As Activity,ViewType As String,bool As Boolean) 
	For Each v As View In ui.GetAllViewsRecursive
		Select ViewType
			Case "Btn"
				If v Is Button Then
					Dim b As Button = v
					b.Enabled = bool
					If bool = False Then
						b.TextColor = Colors.Gray
					Else
						b.TextColor = Colors.White
					End If				
			   End If
		End Select
	Next
End Sub

'Sub MBSerachCSV(var As String) As Int
'	For i = 0 To 120
'		If Main.Str_Modbus(i,1) = var Then Exit
'	Next
'	Return Main.Str_Modbus(i,0)
'End Sub


Sub KeepScreen(b As Boolean)
	If b Then
		'Main.PhoneWakeState.KeepAlive(True)
	Else
		'Main.PhoneWakeState.ReleaseKeepAlive
	End If
End Sub

Sub EmptyEdtToZero(ui As Activity)
	For Each v As View In ui.GetAllViewsRecursive
		If v Is EditText Then
			Dim Edt As EditText = v
			If Edt.Text.Trim = "" Then
				Edt.Text = 0
			End If
	   End If
	Next
End Sub

Sub WeightFormat( mbtab As Int) As String
	'Return NumberFormat2(Main.Flt_MBres(mbtab)/Main.Int_Point(Main.Point),1,Main.Point,Main.Point,False)
End Sub

Sub IntFormat( mbtab As Int) As String
	'Return NumberFormat(Main.Flt_MBres(mbtab),1,0)
End Sub

Sub EdtAddText(e As EditText,s As String)
	e.Text =  s & e.Text
End Sub

'return search index
Sub ArraySearch(a() As String,s As String) As Int
	Dim l As List
	l = a
	Return l.IndexOf(s)
End Sub
