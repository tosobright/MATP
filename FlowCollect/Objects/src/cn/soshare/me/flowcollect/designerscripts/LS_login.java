package cn.soshare.me.flowcollect.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_login{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
//BA.debugLineNum = 3;BA.debugLine="Pnl_Title.SetLeftAndRight(0%x,100%x)"[login/General script]
views.get("pnl_title").vw.setLeft((int)((0d / 100 * width)));
views.get("pnl_title").vw.setWidth((int)((100d / 100 * width) - ((0d / 100 * width))));
//BA.debugLineNum = 4;BA.debugLine="Pnl_Title.SetTopAndBottom(0%y,7%y)"[login/General script]
views.get("pnl_title").vw.setTop((int)((0d / 100 * height)));
views.get("pnl_title").vw.setHeight((int)((7d / 100 * height) - ((0d / 100 * height))));
//BA.debugLineNum = 5;BA.debugLine="Lbl_Action.SetLeftAndRight(0%x,20%x)"[login/General script]
views.get("lbl_action").vw.setLeft((int)((0d / 100 * width)));
views.get("lbl_action").vw.setWidth((int)((20d / 100 * width) - ((0d / 100 * width))));
//BA.debugLineNum = 6;BA.debugLine="Lbl_Action.SetTopAndBottom(0%y,7%y)"[login/General script]
views.get("lbl_action").vw.setTop((int)((0d / 100 * height)));
views.get("lbl_action").vw.setHeight((int)((7d / 100 * height) - ((0d / 100 * height))));
//BA.debugLineNum = 7;BA.debugLine="Lbl_Title.SetLeftAndRight(20%x,80%x)"[login/General script]
views.get("lbl_title").vw.setLeft((int)((20d / 100 * width)));
views.get("lbl_title").vw.setWidth((int)((80d / 100 * width) - ((20d / 100 * width))));
//BA.debugLineNum = 8;BA.debugLine="Lbl_Title.SetTopAndBottom(0%y,7%y)"[login/General script]
views.get("lbl_title").vw.setTop((int)((0d / 100 * height)));
views.get("lbl_title").vw.setHeight((int)((7d / 100 * height) - ((0d / 100 * height))));
//BA.debugLineNum = 9;BA.debugLine="Lbl_Set.SetLeftAndRight(80%x,100%x)"[login/General script]
views.get("lbl_set").vw.setLeft((int)((80d / 100 * width)));
views.get("lbl_set").vw.setWidth((int)((100d / 100 * width) - ((80d / 100 * width))));
//BA.debugLineNum = 10;BA.debugLine="Lbl_Set.SetTopAndBottom(0%y,7%y)"[login/General script]
views.get("lbl_set").vw.setTop((int)((0d / 100 * height)));
views.get("lbl_set").vw.setHeight((int)((7d / 100 * height) - ((0d / 100 * height))));
//BA.debugLineNum = 12;BA.debugLine="Pnl_Login.SetLeftAndRight(0%x,100%x)"[login/General script]
views.get("pnl_login").vw.setLeft((int)((0d / 100 * width)));
views.get("pnl_login").vw.setWidth((int)((100d / 100 * width) - ((0d / 100 * width))));
//BA.debugLineNum = 13;BA.debugLine="Pnl_Login.SetTopAndBottom(7%y,95%y)"[login/General script]
views.get("pnl_login").vw.setTop((int)((7d / 100 * height)));
views.get("pnl_login").vw.setHeight((int)((95d / 100 * height) - ((7d / 100 * height))));
//BA.debugLineNum = 14;BA.debugLine="Edt_User.SetLeftAndRight(20%x,80%x)"[login/General script]
views.get("edt_user").vw.setLeft((int)((20d / 100 * width)));
views.get("edt_user").vw.setWidth((int)((80d / 100 * width) - ((20d / 100 * width))));
//BA.debugLineNum = 15;BA.debugLine="Edt_User.SetTopAndBottom(20%y,30%y)"[login/General script]
views.get("edt_user").vw.setTop((int)((20d / 100 * height)));
views.get("edt_user").vw.setHeight((int)((30d / 100 * height) - ((20d / 100 * height))));
//BA.debugLineNum = 16;BA.debugLine="Edt_PWD.SetLeftAndRight(20%x,80%x)"[login/General script]
views.get("edt_pwd").vw.setLeft((int)((20d / 100 * width)));
views.get("edt_pwd").vw.setWidth((int)((80d / 100 * width) - ((20d / 100 * width))));
//BA.debugLineNum = 17;BA.debugLine="Edt_PWD.SetTopAndBottom(35%y,45%y)"[login/General script]
views.get("edt_pwd").vw.setTop((int)((35d / 100 * height)));
views.get("edt_pwd").vw.setHeight((int)((45d / 100 * height) - ((35d / 100 * height))));
//BA.debugLineNum = 18;BA.debugLine="Btn_Login.SetLeftAndRight(30%x,70%x)"[login/General script]
views.get("btn_login").vw.setLeft((int)((30d / 100 * width)));
views.get("btn_login").vw.setWidth((int)((70d / 100 * width) - ((30d / 100 * width))));
//BA.debugLineNum = 19;BA.debugLine="Btn_Login.SetTopAndBottom(60%y,70%y)"[login/General script]
views.get("btn_login").vw.setTop((int)((60d / 100 * height)));
views.get("btn_login").vw.setHeight((int)((70d / 100 * height) - ((60d / 100 * height))));
//BA.debugLineNum = 21;BA.debugLine="Lbl_Time.SetLeftAndRight(15%x,85%x)"[login/General script]
views.get("lbl_time").vw.setLeft((int)((15d / 100 * width)));
views.get("lbl_time").vw.setWidth((int)((85d / 100 * width) - ((15d / 100 * width))));
//BA.debugLineNum = 22;BA.debugLine="Lbl_Time.SetTopAndBottom(95%y,100%y)"[login/General script]
views.get("lbl_time").vw.setTop((int)((95d / 100 * height)));
views.get("lbl_time").vw.setHeight((int)((100d / 100 * height) - ((95d / 100 * height))));
//BA.debugLineNum = 23;BA.debugLine="Lbl_User.SetLeftAndRight(0%x,15%x)"[login/General script]
views.get("lbl_user").vw.setLeft((int)((0d / 100 * width)));
views.get("lbl_user").vw.setWidth((int)((15d / 100 * width) - ((0d / 100 * width))));
//BA.debugLineNum = 24;BA.debugLine="Lbl_User.SetTopAndBottom(95%y,100%y)"[login/General script]
views.get("lbl_user").vw.setTop((int)((95d / 100 * height)));
views.get("lbl_user").vw.setHeight((int)((100d / 100 * height) - ((95d / 100 * height))));
//BA.debugLineNum = 25;BA.debugLine="Lbl_Status.SetLeftAndRight(85%x,100%x)"[login/General script]
views.get("lbl_status").vw.setLeft((int)((85d / 100 * width)));
views.get("lbl_status").vw.setWidth((int)((100d / 100 * width) - ((85d / 100 * width))));
//BA.debugLineNum = 26;BA.debugLine="Lbl_Status.SetTopAndBottom(95%y,100%y)"[login/General script]
views.get("lbl_status").vw.setTop((int)((95d / 100 * height)));
views.get("lbl_status").vw.setHeight((int)((100d / 100 * height) - ((95d / 100 * height))));

}
}