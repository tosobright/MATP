package cn.soshare.me.flowcollect;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class subfunc {
private static subfunc mostCurrent = new subfunc();
public static Object getObject() {
    throw new RuntimeException("Code module does not support this method.");
}
 public anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4a.objects.MediaPlayerWrapper _mediaplayer = null;
public static boolean _bol_play = false;
public cn.soshare.me.flowcollect.main _main = null;
public static int  _arraysearch(anywheresoftware.b4a.BA _ba,String[] _a,String _s) throws Exception{
anywheresoftware.b4a.objects.collections.List _l = null;
 //BA.debugLineNum = 182;BA.debugLine="Sub ArraySearch(a() As String,s As String) As Int";
 //BA.debugLineNum = 183;BA.debugLine="Dim l As List";
_l = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 184;BA.debugLine="l = a";
_l = anywheresoftware.b4a.keywords.Common.ArrayToList(_a);
 //BA.debugLineNum = 185;BA.debugLine="Return l.IndexOf(s)";
if (true) return _l.IndexOf((Object)(_s));
 //BA.debugLineNum = 186;BA.debugLine="End Sub";
return 0;
}
public static float  _btn_autosize(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ButtonWrapper _view,float _factor) throws Exception{
anywheresoftware.b4a.objects.StringUtils _su = null;
float _size = 0f;
 //BA.debugLineNum = 113;BA.debugLine="Sub Btn_AutoSize(view As Button,factor As Float) A";
 //BA.debugLineNum = 114;BA.debugLine="Dim su As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 115;BA.debugLine="Dim size As Float";
_size = 0f;
 //BA.debugLineNum = 116;BA.debugLine="view.Text = \"沙TOSO\"";
_view.setText((Object)("沙TOSO"));
 //BA.debugLineNum = 117;BA.debugLine="For size = 2 To 300";
{
final double step99 = 1;
final double limit99 = (float) (300);
for (_size = (float) (2); (step99 > 0 && _size <= limit99) || (step99 < 0 && _size >= limit99); _size = ((float)(0 + _size + step99))) {
 //BA.debugLineNum = 118;BA.debugLine="view.TextSize = size";
_view.setTextSize(_size);
 //BA.debugLineNum = 119;BA.debugLine="If su.MeasureMultilineTextHeight(view,view.Text)";
if (_su.MeasureMultilineTextHeight((android.widget.TextView)(_view.getObject()),_view.getText())>_view.getHeight()) { 
if (true) break;};
 }
};
 //BA.debugLineNum = 121;BA.debugLine="view.TextSize = (size-0.5) * factor";
_view.setTextSize((float) ((_size-0.5)*_factor));
 //BA.debugLineNum = 122;BA.debugLine="Return	(size-0.5) * factor";
if (true) return (float) ((_size-0.5)*_factor);
 //BA.debugLineNum = 123;BA.debugLine="End Sub";
return 0f;
}
public static String  _convcsv(anywheresoftware.b4a.BA _ba,String _csv,String[][] _arr) throws Exception{
anywheresoftware.b4a.objects.StringUtils _su = null;
anywheresoftware.b4a.objects.collections.List _list1 = null;
int _i = 0;
String[] _scol = null;
int _j = 0;
 //BA.debugLineNum = 55;BA.debugLine="Sub convCSV(csv As String,Arr(,) As String)";
 //BA.debugLineNum = 56;BA.debugLine="Dim su As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 57;BA.debugLine="Dim list1 As List";
_list1 = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 58;BA.debugLine="list1 = su.LoadCSV(File.DirAssets, csv, \",\")";
_list1 = _su.LoadCSV(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),_csv,BA.ObjectToChar(","));
 //BA.debugLineNum = 59;BA.debugLine="For i = 0 To list1.Size-1";
{
final int step47 = 1;
final int limit47 = (int) (_list1.getSize()-1);
for (_i = (int) (0); (step47 > 0 && _i <= limit47) || (step47 < 0 && _i >= limit47); _i = ((int)(0 + _i + step47))) {
 //BA.debugLineNum = 60;BA.debugLine="Dim sCol() As String";
_scol = new String[(int) (0)];
java.util.Arrays.fill(_scol,"");
 //BA.debugLineNum = 61;BA.debugLine="sCol = list1.Get(i)";
_scol = (String[])(_list1.Get(_i));
 //BA.debugLineNum = 62;BA.debugLine="For j = 0 To sCol.Length-1";
{
final int step50 = 1;
final int limit50 = (int) (_scol.length-1);
for (_j = (int) (0); (step50 > 0 && _j <= limit50) || (step50 < 0 && _j >= limit50); _j = ((int)(0 + _j + step50))) {
 //BA.debugLineNum = 63;BA.debugLine="Arr(i,j) =  sCol(j)";
_arr[_i][_j] = _scol[_j];
 }
};
 }
};
 //BA.debugLineNum = 66;BA.debugLine="End Sub";
return "";
}
public static float  _edt_autosize(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.EditTextWrapper _view,float _factor) throws Exception{
anywheresoftware.b4a.objects.StringUtils _su = null;
float _size = 0f;
 //BA.debugLineNum = 89;BA.debugLine="Sub Edt_AutoSize(view As EditText,factor As Float)";
 //BA.debugLineNum = 90;BA.debugLine="Dim su As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 91;BA.debugLine="Dim size As Float";
_size = 0f;
 //BA.debugLineNum = 92;BA.debugLine="view.Text = \"沙TOSO\"";
_view.setText((Object)("沙TOSO"));
 //BA.debugLineNum = 93;BA.debugLine="For size = 2 To 300";
{
final double step77 = 1;
final double limit77 = (float) (300);
for (_size = (float) (2); (step77 > 0 && _size <= limit77) || (step77 < 0 && _size >= limit77); _size = ((float)(0 + _size + step77))) {
 //BA.debugLineNum = 94;BA.debugLine="view.TextSize = size";
_view.setTextSize(_size);
 //BA.debugLineNum = 95;BA.debugLine="If su.MeasureMultilineTextHeight(view,view.Text)";
if (_su.MeasureMultilineTextHeight((android.widget.TextView)(_view.getObject()),_view.getText())>_view.getHeight()) { 
if (true) break;};
 }
};
 //BA.debugLineNum = 97;BA.debugLine="view.TextSize = (size-0.5) * factor";
_view.setTextSize((float) ((_size-0.5)*_factor));
 //BA.debugLineNum = 98;BA.debugLine="Return	(size-0.5) * factor";
if (true) return (float) ((_size-0.5)*_factor);
 //BA.debugLineNum = 99;BA.debugLine="End Sub";
return 0f;
}
public static String  _edtaddtext(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.EditTextWrapper _e,String _s) throws Exception{
 //BA.debugLineNum = 177;BA.debugLine="Sub EdtAddText(e As EditText,s As String)";
 //BA.debugLineNum = 178;BA.debugLine="e.Text =  s & e.Text";
_e.setText((Object)(_s+_e.getText()));
 //BA.debugLineNum = 179;BA.debugLine="End Sub";
return "";
}
public static String  _emptyedttozero(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ActivityWrapper _ui) throws Exception{
anywheresoftware.b4a.objects.ConcreteViewWrapper _v = null;
anywheresoftware.b4a.objects.EditTextWrapper _edt = null;
 //BA.debugLineNum = 158;BA.debugLine="Sub EmptyEdtToZero(ui As Activity)";
 //BA.debugLineNum = 159;BA.debugLine="For Each v As View In ui.GetAllViewsRecursive";
_v = new anywheresoftware.b4a.objects.ConcreteViewWrapper();
final anywheresoftware.b4a.BA.IterableList group128 = _ui.GetAllViewsRecursive();
final int groupLen128 = group128.getSize();
for (int index128 = 0;index128 < groupLen128 ;index128++){
_v.setObject((android.view.View)(group128.Get(index128)));
 //BA.debugLineNum = 160;BA.debugLine="If v Is EditText Then";
if (_v.getObjectOrNull() instanceof android.widget.EditText) { 
 //BA.debugLineNum = 161;BA.debugLine="Dim Edt As EditText = v";
_edt = new anywheresoftware.b4a.objects.EditTextWrapper();
_edt.setObject((android.widget.EditText)(_v.getObject()));
 //BA.debugLineNum = 162;BA.debugLine="If Edt.Text.Trim = \"\" Then";
if ((_edt.getText().trim()).equals("")) { 
 //BA.debugLineNum = 163;BA.debugLine="Edt.Text = 0";
_edt.setText((Object)(0));
 };
 };
 }
;
 //BA.debugLineNum = 167;BA.debugLine="End Sub";
return "";
}
public static String  _fnumtostr(anywheresoftware.b4a.BA _ba,float _n) throws Exception{
String _s = "";
 //BA.debugLineNum = 83;BA.debugLine="Sub fNumToStr(n As Float) As String";
 //BA.debugLineNum = 84;BA.debugLine="Dim s As String";
_s = "";
 //BA.debugLineNum = 85;BA.debugLine="s = n";
_s = BA.NumberToString(_n);
 //BA.debugLineNum = 86;BA.debugLine="Return s";
if (true) return _s;
 //BA.debugLineNum = 87;BA.debugLine="End Sub";
return "";
}
public static String  _inputedt(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.EditTextWrapper _edt,String _hint,String _inputype) throws Exception{
anywheresoftware.b4a.objects.IME _ime1 = null;
 //BA.debugLineNum = 21;BA.debugLine="Sub InputEdt(edt As EditText,hint As String,inputy";
 //BA.debugLineNum = 22;BA.debugLine="Dim IME1 As IME";
_ime1 = new anywheresoftware.b4a.objects.IME();
 //BA.debugLineNum = 24;BA.debugLine="edt.Color = Colors.LightGray";
_edt.setColor(anywheresoftware.b4a.keywords.Common.Colors.LightGray);
 //BA.debugLineNum = 25;BA.debugLine="edt.TextColor = Colors.Black";
_edt.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 26;BA.debugLine="edt.Hint = hint";
_edt.setHint(_hint);
 //BA.debugLineNum = 27;BA.debugLine="edt.SingleLine = True";
_edt.setSingleLine(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 28;BA.debugLine="Select inputype";
switch (BA.switchObjectToInt(_inputype,"int","phone","float","IP")) {
case 0:
 //BA.debugLineNum = 30;BA.debugLine="edt.InputType =edt.INPUT_TYPE_NUMBERS";
_edt.setInputType(_edt.INPUT_TYPE_NUMBERS);
 break;
case 1:
 //BA.debugLineNum = 32;BA.debugLine="edt.InputType =edt.INPUT_TYPE_PHONE";
_edt.setInputType(_edt.INPUT_TYPE_PHONE);
 break;
case 2:
 //BA.debugLineNum = 34;BA.debugLine="edt.InputType =edt.INPUT_TYPE_DECIMAL_NUMBERS";
_edt.setInputType(_edt.INPUT_TYPE_DECIMAL_NUMBERS);
 break;
case 3:
 //BA.debugLineNum = 36;BA.debugLine="IME1.SetCustomFilter(edt, edt.INPUT_TYPE_TEXT,";
_ime1.SetCustomFilter((android.widget.EditText)(_edt.getObject()),_edt.INPUT_TYPE_TEXT,"0123456789.");
 break;
default:
 //BA.debugLineNum = 39;BA.debugLine="edt.InputType =edt.INPUT_TYPE_TEXT";
_edt.setInputType(_edt.INPUT_TYPE_TEXT);
 break;
}
;
 //BA.debugLineNum = 41;BA.debugLine="End Sub";
return "";
}
public static String  _intformat(anywheresoftware.b4a.BA _ba,int _mbtab) throws Exception{
 //BA.debugLineNum = 173;BA.debugLine="Sub IntFormat( mbtab As Int) As String";
 //BA.debugLineNum = 175;BA.debugLine="End Sub";
return "";
}
public static String  _keepscreen(anywheresoftware.b4a.BA _ba,boolean _b) throws Exception{
 //BA.debugLineNum = 150;BA.debugLine="Sub KeepScreen(b As Boolean)";
 //BA.debugLineNum = 151;BA.debugLine="If b Then";
if (_b) { 
 }else {
 };
 //BA.debugLineNum = 156;BA.debugLine="End Sub";
return "";
}
public static float  _lbl_autosize(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.LabelWrapper _view,float _factor) throws Exception{
anywheresoftware.b4a.objects.StringUtils _su = null;
float _size = 0f;
 //BA.debugLineNum = 101;BA.debugLine="Sub Lbl_AutoSize(view As Label,factor As Float) As";
 //BA.debugLineNum = 102;BA.debugLine="Dim su As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 103;BA.debugLine="Dim size As Float";
_size = 0f;
 //BA.debugLineNum = 104;BA.debugLine="view.Text = \"HMI\"";
_view.setText((Object)("HMI"));
 //BA.debugLineNum = 105;BA.debugLine="For size = 2 To 300";
{
final double step88 = 1;
final double limit88 = (float) (300);
for (_size = (float) (2); (step88 > 0 && _size <= limit88) || (step88 < 0 && _size >= limit88); _size = ((float)(0 + _size + step88))) {
 //BA.debugLineNum = 106;BA.debugLine="view.TextSize = size";
_view.setTextSize(_size);
 //BA.debugLineNum = 107;BA.debugLine="If su.MeasureMultilineTextHeight(view,view.Text)";
if (_su.MeasureMultilineTextHeight((android.widget.TextView)(_view.getObject()),_view.getText())>_view.getHeight()) { 
if (true) break;};
 }
};
 //BA.debugLineNum = 109;BA.debugLine="view.TextSize = (size-0.5) * factor";
_view.setTextSize((float) ((_size-0.5)*_factor));
 //BA.debugLineNum = 110;BA.debugLine="Return	(size-0.5) * factor";
if (true) return (float) ((_size-0.5)*_factor);
 //BA.debugLineNum = 111;BA.debugLine="End Sub";
return 0f;
}
public static String  _lbl_title(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ActivityWrapper _ui,anywheresoftware.b4a.objects.LabelWrapper _lbl,String _text) throws Exception{
 //BA.debugLineNum = 43;BA.debugLine="Sub Lbl_title(ui As Activity,Lbl As Label,text As";
 //BA.debugLineNum = 44;BA.debugLine="Lbl.RemoveView";
_lbl.RemoveView();
 //BA.debugLineNum = 45;BA.debugLine="ui.AddView(Lbl,20%X,2%Y,60%X,15%y)";
_ui.AddView((android.view.View)(_lbl.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (20),_ba),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (2),_ba),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (60),_ba),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (15),_ba));
 //BA.debugLineNum = 46;BA.debugLine="Lbl_AutoSize(Lbl,0.8)";
_lbl_autosize(_ba,_lbl,(float) (0.8));
 //BA.debugLineNum = 47;BA.debugLine="Lbl.Color = Colors.Transparent";
_lbl.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 48;BA.debugLine="Lbl.TextColor = Colors.Black";
_lbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 49;BA.debugLine="Lbl.Gravity = Gravity.CENTER";
_lbl.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 50;BA.debugLine="If text <> \"\" Then";
if ((_text).equals("") == false) { 
 //BA.debugLineNum = 51;BA.debugLine="Lbl.Text = text";
_lbl.setText((Object)(_text));
 };
 //BA.debugLineNum = 53;BA.debugLine="End Sub";
return "";
}
public static String  _playmp3(anywheresoftware.b4a.BA _ba,String _filename,boolean _playloop) throws Exception{
 //BA.debugLineNum = 68;BA.debugLine="Sub PlayMP3(FileName As String,PlayLoop As Boolean";
 //BA.debugLineNum = 69;BA.debugLine="If MediaPlayer.IsPlaying = True Then Return";
if (_mediaplayer.IsPlaying()==anywheresoftware.b4a.keywords.Common.True) { 
if (true) return "";};
 //BA.debugLineNum = 70;BA.debugLine="MediaPlayer.Load(File.DirAssets,FileName)";
_mediaplayer.Load(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),_filename);
 //BA.debugLineNum = 71;BA.debugLine="MediaPlayer.Play'播放";
_mediaplayer.Play();
 //BA.debugLineNum = 72;BA.debugLine="If(PlayLoop = True) Then";
if ((_playloop==anywheresoftware.b4a.keywords.Common.True)) { 
 //BA.debugLineNum = 73;BA.debugLine="MediaPlayer.Looping=True'True:播放完自动重新开始 False:播放";
_mediaplayer.setLooping(anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 75;BA.debugLine="Bol_Play = True";
_bol_play = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 76;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 3;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 6;BA.debugLine="Dim MediaPlayer As MediaPlayer";
_mediaplayer = new anywheresoftware.b4a.objects.MediaPlayerWrapper();
 //BA.debugLineNum = 7;BA.debugLine="Dim Bol_Play As Boolean";
_bol_play = false;
 //BA.debugLineNum = 8;BA.debugLine="MediaPlayer.Initialize'初始化";
_mediaplayer.Initialize();
 //BA.debugLineNum = 9;BA.debugLine="End Sub";
return "";
}
public static String  _stopmp3(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 78;BA.debugLine="Sub StopMp3()";
 //BA.debugLineNum = 79;BA.debugLine="If Bol_Play = True Then	MediaPlayer.Stop";
if (_bol_play==anywheresoftware.b4a.keywords.Common.True) { 
_mediaplayer.Stop();};
 //BA.debugLineNum = 80;BA.debugLine="Bol_Play = False";
_bol_play = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 81;BA.debugLine="End Sub";
return "";
}
public static String  _viewctl(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ActivityWrapper _ui,String _viewtype,boolean _bool) throws Exception{
anywheresoftware.b4a.objects.ConcreteViewWrapper _v = null;
anywheresoftware.b4a.objects.ButtonWrapper _b = null;
 //BA.debugLineNum = 125;BA.debugLine="Sub ViewCtl(ui As Activity,ViewType As String,bool";
 //BA.debugLineNum = 126;BA.debugLine="For Each v As View In ui.GetAllViewsRecursive";
_v = new anywheresoftware.b4a.objects.ConcreteViewWrapper();
final anywheresoftware.b4a.BA.IterableList group107 = _ui.GetAllViewsRecursive();
final int groupLen107 = group107.getSize();
for (int index107 = 0;index107 < groupLen107 ;index107++){
_v.setObject((android.view.View)(group107.Get(index107)));
 //BA.debugLineNum = 127;BA.debugLine="Select ViewType";
switch (BA.switchObjectToInt(_viewtype,"Btn")) {
case 0:
 //BA.debugLineNum = 129;BA.debugLine="If v Is Button Then";
if (_v.getObjectOrNull() instanceof android.widget.Button) { 
 //BA.debugLineNum = 130;BA.debugLine="Dim b As Button = v";
_b = new anywheresoftware.b4a.objects.ButtonWrapper();
_b.setObject((android.widget.Button)(_v.getObject()));
 //BA.debugLineNum = 131;BA.debugLine="b.Enabled = bool";
_b.setEnabled(_bool);
 //BA.debugLineNum = 132;BA.debugLine="If bool = False Then";
if (_bool==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 133;BA.debugLine="b.TextColor = Colors.Gray";
_b.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Gray);
 }else {
 //BA.debugLineNum = 135;BA.debugLine="b.TextColor = Colors.White";
_b.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 };
 };
 break;
}
;
 }
;
 //BA.debugLineNum = 140;BA.debugLine="End Sub";
return "";
}
public static String  _waitms(anywheresoftware.b4a.BA _ba,int _milisecs) throws Exception{
long _ti = 0L;
 //BA.debugLineNum = 11;BA.debugLine="Sub Waitms(milisecs As Int)";
 //BA.debugLineNum = 12;BA.debugLine="Dim ti As Long";
_ti = 0L;
 //BA.debugLineNum = 14;BA.debugLine="ti = DateTime.Now + (milisecs)";
_ti = (long) (anywheresoftware.b4a.keywords.Common.DateTime.getNow()+(_milisecs));
 //BA.debugLineNum = 15;BA.debugLine="Do While DateTime.Now < ti";
while (anywheresoftware.b4a.keywords.Common.DateTime.getNow()<_ti) {
 //BA.debugLineNum = 16;BA.debugLine="DoEvents";
anywheresoftware.b4a.keywords.Common.DoEvents();
 }
;
 //BA.debugLineNum = 18;BA.debugLine="Return";
if (true) return "";
 //BA.debugLineNum = 19;BA.debugLine="End Sub";
return "";
}
public static String  _weightformat(anywheresoftware.b4a.BA _ba,int _mbtab) throws Exception{
 //BA.debugLineNum = 169;BA.debugLine="Sub WeightFormat( mbtab As Int) As String";
 //BA.debugLineNum = 171;BA.debugLine="End Sub";
return "";
}
}
