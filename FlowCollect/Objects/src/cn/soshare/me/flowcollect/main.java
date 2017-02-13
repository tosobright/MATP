package cn.soshare.me.flowcollect;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

import anywheresoftware.b4a.B4AMenuItem;
import android.app.Activity;
import android.os.Bundle;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.B4AActivity;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import java.lang.reflect.InvocationTargetException;
import anywheresoftware.b4a.B4AUncaughtException;
import anywheresoftware.b4a.debug.*;
import java.lang.ref.WeakReference;

public class main extends Activity implements B4AActivity{
	public static main mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = false;
	public static final boolean includeTitle = false;
    public static WeakReference<Activity> previousOne;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (isFirst) {
			processBA = new BA(this.getApplicationContext(), null, null, "cn.soshare.me.flowcollect", "cn.soshare.me.flowcollect.main");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (main).");
				p.finish();
			}
		}
        processBA.runHook("oncreate", this, null);
		if (!includeTitle) {
        	this.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
        }
        if (fullScreen) {
        	getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
		mostCurrent = this;
        processBA.sharedProcessBA.activityBA = null;
		layout = new BALayout(this);
		setContentView(layout);
		afterFirstLayout = false;
		BA.handler.postDelayed(new WaitForLayout(), 5);

	}
	private static class WaitForLayout implements Runnable {
		public void run() {
			if (afterFirstLayout)
				return;
			if (mostCurrent == null)
				return;
            
			if (mostCurrent.layout.getWidth() == 0) {
				BA.handler.postDelayed(this, 5);
				return;
			}
			mostCurrent.layout.getLayoutParams().height = mostCurrent.layout.getHeight();
			mostCurrent.layout.getLayoutParams().width = mostCurrent.layout.getWidth();
			afterFirstLayout = true;
			mostCurrent.afterFirstLayout();
		}
	}
	private void afterFirstLayout() {
        if (this != mostCurrent)
			return;
		activityBA = new BA(this, layout, processBA, "cn.soshare.me.flowcollect", "cn.soshare.me.flowcollect.main");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "cn.soshare.me.flowcollect.main", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (main) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (main) Resume **");
        processBA.raiseEvent(null, "activity_resume");
        if (android.os.Build.VERSION.SDK_INT >= 11) {
			try {
				android.app.Activity.class.getMethod("invalidateOptionsMenu").invoke(this,(Object[]) null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void addMenuItem(B4AMenuItem item) {
		if (menuItems == null)
			menuItems = new java.util.ArrayList<B4AMenuItem>();
		menuItems.add(item);
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
        try {
            if (processBA.subExists("activity_actionbarhomeclick")) {
                Class.forName("android.app.ActionBar").getMethod("setHomeButtonEnabled", boolean.class).invoke(
                    getClass().getMethod("getActionBar").invoke(this), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (processBA.runHook("oncreateoptionsmenu", this, new Object[] {menu}))
            return true;
		if (menuItems == null)
			return false;
		for (B4AMenuItem bmi : menuItems) {
			android.view.MenuItem mi = menu.add(bmi.title);
			if (bmi.drawable != null)
				mi.setIcon(bmi.drawable);
            if (android.os.Build.VERSION.SDK_INT >= 11) {
				try {
                    if (bmi.addToBar) {
				        android.view.MenuItem.class.getMethod("setShowAsAction", int.class).invoke(mi, 1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mi.setOnMenuItemClickListener(new B4AMenuItemsClickListener(bmi.eventName.toLowerCase(BA.cul)));
		}
        
		return true;
	}   
 @Override
 public boolean onOptionsItemSelected(android.view.MenuItem item) {
    if (item.getItemId() == 16908332) {
        processBA.raiseEvent(null, "activity_actionbarhomeclick");
        return true;
    }
    else
        return super.onOptionsItemSelected(item); 
}
@Override
 public boolean onPrepareOptionsMenu(android.view.Menu menu) {
    super.onPrepareOptionsMenu(menu);
    processBA.runHook("onprepareoptionsmenu", this, new Object[] {menu});
    return true;
    
 }
 protected void onStart() {
    super.onStart();
    processBA.runHook("onstart", this, null);
}
 protected void onStop() {
    super.onStop();
    processBA.runHook("onstop", this, null);
}
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       if (processBA.subExists("activity_windowfocuschanged"))
           processBA.raiseEvent2(null, true, "activity_windowfocuschanged", false, hasFocus);
    }
	private class B4AMenuItemsClickListener implements android.view.MenuItem.OnMenuItemClickListener {
		private final String eventName;
		public B4AMenuItemsClickListener(String eventName) {
			this.eventName = eventName;
		}
		public boolean onMenuItemClick(android.view.MenuItem item) {
			processBA.raiseEvent(item.getTitle(), eventName + "_click");
			return true;
		}
	}
    public static Class<?> getObject() {
		return main.class;
	}
    private Boolean onKeySubExist = null;
    private Boolean onKeyUpSubExist = null;
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
		if (onKeySubExist == null)
			onKeySubExist = processBA.subExists("activity_keypress");
		if (onKeySubExist) {
			if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK &&
					android.os.Build.VERSION.SDK_INT >= 18) {
				HandleKeyDelayed hk = new HandleKeyDelayed();
				hk.kc = keyCode;
				BA.handler.post(hk);
				return true;
			}
			else {
				boolean res = new HandleKeyDelayed().runDirectly(keyCode);
				if (res)
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	private class HandleKeyDelayed implements Runnable {
		int kc;
		public void run() {
			runDirectly(kc);
		}
		public boolean runDirectly(int keyCode) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keypress", false, keyCode);
			if (res == null || res == true) {
                return true;
            }
            else if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK) {
				finish();
				return true;
			}
            return false;
		}
		
	}
    @Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
		if (onKeyUpSubExist == null)
			onKeyUpSubExist = processBA.subExists("activity_keyup");
		if (onKeyUpSubExist) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keyup", false, keyCode);
			if (res == null || res == true)
				return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
		this.setIntent(intent);
        processBA.runHook("onnewintent", this, new Object[] {intent});
	}
    @Override 
	public void onPause() {
		super.onPause();
        if (_activity == null) //workaround for emulator bug (Issue 2423)
            return;
		anywheresoftware.b4a.Msgbox.dismiss(true);
        BA.LogInfo("** Activity (main) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        processBA.raiseEvent2(_activity, true, "activity_pause", false, activityBA.activity.isFinishing());		
        processBA.setActivityPaused(true);
        mostCurrent = null;
        if (!activityBA.activity.isFinishing())
			previousOne = new WeakReference<Activity>(this);
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        processBA.runHook("onpause", this, null);
	}

	@Override
	public void onDestroy() {
        super.onDestroy();
		previousOne = null;
        processBA.runHook("ondestroy", this, null);
	}
    @Override 
	public void onResume() {
		super.onResume();
        mostCurrent = this;
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (activityBA != null) { //will be null during activity create (which waits for AfterLayout).
        	ResumeMessage rm = new ResumeMessage(mostCurrent);
        	BA.handler.post(rm);
        }
        processBA.runHook("onresume", this, null);
	}
    private static class ResumeMessage implements Runnable {
    	private final WeakReference<Activity> activity;
    	public ResumeMessage(Activity activity) {
    		this.activity = new WeakReference<Activity>(activity);
    	}
		public void run() {
			if (mostCurrent == null || mostCurrent != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (main) Resume **");
		    processBA.raiseEvent(mostCurrent._activity, "activity_resume", (Object[])null);
		}
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
	      android.content.Intent data) {
		processBA.onActivityResult(requestCode, resultCode, data);
        processBA.runHook("onactivityresult", this, new Object[] {requestCode, resultCode});
	}
	private static void initializeGlobals() {
		processBA.raiseEvent2(null, true, "globals", false, (Object[])null);
	}

public anywheresoftware.b4a.keywords.Common __c = null;
public static com.tomlost.MSSQL.MSSQL _db = null;
public static anywheresoftware.b4a.objects.collections.List _lst_result = null;
public static anywheresoftware.b4a.objects.Timer _tim = null;
public static String _str_setbuff = "";
public static String _str_permit = "";
public static anywheresoftware.b4a.phone.Phone _phone = null;
public static anywheresoftware.b4j.object.JavaObject _battery = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_login = null;
public anywheresoftware.b4a.objects.EditTextWrapper _edt_pwd = null;
public anywheresoftware.b4a.objects.EditTextWrapper _edt_user = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnl_login = null;
public anywheresoftware.b4a.objects.ScrollViewWrapper[] _scrview = null;
public anywheresoftware.b4a.objects.LabelWrapper[] _lbl_arr = null;
public static String[] _lbl_txt = null;
public anywheresoftware.b4a.objects.EditTextWrapper[] _edt_arr = null;
public anywheresoftware.b4a.objects.SpinnerWrapper _spn_sequence = null;
public anywheresoftware.b4a.objects.SpinnerWrapper _spn_inouttype = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_post = null;
public anywheresoftware.b4a.objects.IME _ime = null;
public ice.zxing.b4aZXingLib _zx = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnl_title = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_action = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_set = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_title = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_time = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_user = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_status = null;
public anywheresoftware.b4a.objects.ListViewWrapper _lst_func = null;
public anywheresoftware.b4a.objects.ListViewWrapper _lst_set = null;
public wifi.MLwifi _wifi = null;
public wifi.MLwifi.MLWifiScanner _wifiscan = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnl_wifiset = null;
public anywheresoftware.b4a.objects.SpinnerWrapper _spn_wifilist = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_wificonnect = null;
public cn.soshare.me.flowcollect.subfunc _subfunc = null;

public static boolean isAnyActivityVisible() {
    boolean vis = false;
vis = vis | (main.mostCurrent != null);
return vis;}
public static String  _activity_create(boolean _firsttime) throws Exception{
anywheresoftware.b4a.objects.LabelWrapper _lbl_wel = null;
 //BA.debugLineNum = 84;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 87;BA.debugLine="Activity.LoadLayout(\"login\")";
mostCurrent._activity.LoadLayout("login",mostCurrent.activityBA);
 //BA.debugLineNum = 88;BA.debugLine="Pnl_Title.Visible = False";
mostCurrent._pnl_title.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 89;BA.debugLine="Pnl_Login.Visible = False";
mostCurrent._pnl_login.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 90;BA.debugLine="Lbl_Time.Visible = False";
mostCurrent._lbl_time.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 91;BA.debugLine="Lbl_User.Visible = False";
mostCurrent._lbl_user.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 92;BA.debugLine="Lbl_Status.Visible = False";
mostCurrent._lbl_status.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 93;BA.debugLine="If FirstTime Then";
if (_firsttime) { 
 //BA.debugLineNum = 94;BA.debugLine="Dim lbl_wel As Label";
_lbl_wel = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 95;BA.debugLine="lbl_wel.Initialize(\"\")";
_lbl_wel.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 96;BA.debugLine="Activity.addview(lbl_wel,10%x,40%y,80%x,20%y)";
mostCurrent._activity.AddView((android.view.View)(_lbl_wel.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (10),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (40),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (80),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (20),mostCurrent.activityBA));
 //BA.debugLineNum = 97;BA.debugLine="SubFunc.Lbl_AutoSize(lbl_wel,0.4)";
mostCurrent._subfunc._lbl_autosize(mostCurrent.activityBA,_lbl_wel,(float) (0.4));
 //BA.debugLineNum = 98;BA.debugLine="lbl_wel.Text = \"Welcome\"";
_lbl_wel.setText((Object)("Welcome"));
 //BA.debugLineNum = 99;BA.debugLine="lbl_wel.Gravity = Gravity.CENTER";
_lbl_wel.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 100;BA.debugLine="PanelSet";
_panelset();
 //BA.debugLineNum = 101;BA.debugLine="SubFunc.Waitms(2000)";
mostCurrent._subfunc._waitms(mostCurrent.activityBA,(int) (2000));
 //BA.debugLineNum = 102;BA.debugLine="lbl_wel.Visible = False";
_lbl_wel.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 103;BA.debugLine="Battery.InitializeContext";
_battery.InitializeContext(processBA);
 //BA.debugLineNum = 104;BA.debugLine="DisableStrictMode";
_disablestrictmode();
 }else {
 //BA.debugLineNum = 106;BA.debugLine="PanelSet";
_panelset();
 };
 //BA.debugLineNum = 108;BA.debugLine="Pnl_Title.Visible = True";
mostCurrent._pnl_title.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 109;BA.debugLine="Pnl_Login.Visible = True";
mostCurrent._pnl_login.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 110;BA.debugLine="Lbl_Time.Visible = True";
mostCurrent._lbl_time.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 111;BA.debugLine="Lbl_User.Visible = True";
mostCurrent._lbl_user.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 112;BA.debugLine="Lbl_Status.Visible = True";
mostCurrent._lbl_status.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 115;BA.debugLine="db.setDatabase(\"ms2057631.xincache1.cn\",\"ms205763";
_db.setDatabase("ms2057631.xincache1.cn","ms2057631","ms2057631","taosha881021");
 //BA.debugLineNum = 117;BA.debugLine="ime.Initialize(\"ime\")";
mostCurrent._ime.Initialize("ime");
 //BA.debugLineNum = 118;BA.debugLine="ime.AddHeightChangedEvent";
mostCurrent._ime.AddHeightChangedEvent(mostCurrent.activityBA);
 //BA.debugLineNum = 120;BA.debugLine="tim.Initialize(\"tim\",200)";
_tim.Initialize(processBA,"tim",(long) (200));
 //BA.debugLineNum = 121;BA.debugLine="tim.Enabled = True";
_tim.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 123;BA.debugLine="AddOverlay";
_addoverlay();
 //BA.debugLineNum = 124;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 315;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 316;BA.debugLine="If KeyCode = KeyCodes.KEYCODE_BACK Then";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK) { 
 //BA.debugLineNum = 317;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 319;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 311;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 313;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 307;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 309;BA.debugLine="End Sub";
return "";
}
public static String  _addoverlay() throws Exception{
anywheresoftware.b4a.objects.LabelWrapper _mview = null;
anywheresoftware.b4j.object.JavaObject _mlp = null;
int _vtype = 0;
int _pixelformat = 0;
anywheresoftware.b4j.object.JavaObject _windowmanager = null;
 //BA.debugLineNum = 154;BA.debugLine="Sub AddOverlay";
 //BA.debugLineNum = 155;BA.debugLine="Dim mView As Label";
_mview = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 156;BA.debugLine="mView.Initialize(\"\")";
_mview.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 157;BA.debugLine="mView.Text = \"BYD IGBT Collect System\"";
_mview.setText((Object)("BYD IGBT Collect System"));
 //BA.debugLineNum = 158;BA.debugLine="mView.Color = Colors.RGB(0xF4,0x3A,0x3A)";
_mview.setColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (0xf4),(int) (0x3a),(int) (0x3a)));
 //BA.debugLineNum = 159;BA.debugLine="mView.TextColor = Colors.Black";
_mview.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 160;BA.debugLine="Dim mlp As JavaObject";
_mlp = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 161;BA.debugLine="Dim vtype As Int = -1, pixelFormat As Int = -3";
_vtype = (int) (-1);
_pixelformat = (int) (-3);
 //BA.debugLineNum = 162;BA.debugLine="mlp.InitializeNewInstance(\"android.view.Window";
_mlp.InitializeNewInstance("android.view.WindowManager$LayoutParams",new Object[]{(Object)(_vtype),(Object)(_statusbarheight()),(Object)(2010),(Object)(296),(Object)(_pixelformat)});
 //BA.debugLineNum = 163;BA.debugLine="mlp.SetField(\"gravity\", Bit.Or(Gravity.TOP, Gr";
_mlp.SetField("gravity",(Object)(anywheresoftware.b4a.keywords.Common.Bit.Or(anywheresoftware.b4a.keywords.Common.Gravity.TOP,anywheresoftware.b4a.keywords.Common.Gravity.CENTER)));
 //BA.debugLineNum = 164;BA.debugLine="Dim windowManager As JavaObject = GetContext.R";
_windowmanager = new anywheresoftware.b4j.object.JavaObject();
_windowmanager.setObject((java.lang.Object)(_getcontext().RunMethod("getSystemService",new Object[]{(Object)("window")})));
 //BA.debugLineNum = 165;BA.debugLine="windowManager.RunMethod(\"addView\", Array(mView";
_windowmanager.RunMethod("addView",new Object[]{(Object)(_mview.getObject()),(Object)(_mlp.getObject())});
 //BA.debugLineNum = 166;BA.debugLine="End Sub";
return "";
}
public static String  _btn_login_up() throws Exception{
String _s = "";
String[] _res = null;
 //BA.debugLineNum = 404;BA.debugLine="Sub Btn_Login_Up";
 //BA.debugLineNum = 405;BA.debugLine="ProgressDialogShow(\"登录中...\")";
anywheresoftware.b4a.keywords.Common.ProgressDialogShow(mostCurrent.activityBA,"登录中...");
 //BA.debugLineNum = 406;BA.debugLine="SubFunc.Waitms(500)";
mostCurrent._subfunc._waitms(mostCurrent.activityBA,(int) (500));
 //BA.debugLineNum = 407;BA.debugLine="Try";
try { //BA.debugLineNum = 408;BA.debugLine="lst_result = db.Query(\"SELECT * FROM dbo.[user]";
_lst_result.setObject((java.util.List)(_db.Query("SELECT * FROM dbo.[user] WHERE id = '"+mostCurrent._edt_user.getText().trim()+"'")));
 //BA.debugLineNum = 409;BA.debugLine="SubFunc.Waitms(1000)";
mostCurrent._subfunc._waitms(mostCurrent.activityBA,(int) (1000));
 } 
       catch (Exception e302) {
			processBA.setLastException(e302); //BA.debugLineNum = 411;BA.debugLine="Msgbox(\"Server Error\",\"login\")";
anywheresoftware.b4a.keywords.Common.Msgbox("Server Error","login",mostCurrent.activityBA);
 //BA.debugLineNum = 412;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 413;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 415;BA.debugLine="If lst_result.Size < 2 Then";
if (_lst_result.getSize()<2) { 
 //BA.debugLineNum = 416;BA.debugLine="Msgbox(\"id error\",\"login\")";
anywheresoftware.b4a.keywords.Common.Msgbox("id error","login",mostCurrent.activityBA);
 //BA.debugLineNum = 417;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 418;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 420;BA.debugLine="Dim s As String";
_s = "";
 //BA.debugLineNum = 421;BA.debugLine="s = lst_result.Get(1)";
_s = BA.ObjectToString(_lst_result.Get((int) (1)));
 //BA.debugLineNum = 422;BA.debugLine="s = s.SubString2(s.IndexOf(\"[\") + 1, s.IndexOf(\"]";
_s = _s.substring((int) (_s.indexOf("[")+1),_s.indexOf("]")).trim();
 //BA.debugLineNum = 423;BA.debugLine="Dim res() As String";
_res = new String[(int) (0)];
java.util.Arrays.fill(_res,"");
 //BA.debugLineNum = 424;BA.debugLine="res =Regex.Split(\",\",s)";
_res = anywheresoftware.b4a.keywords.Common.Regex.Split(",",_s);
 //BA.debugLineNum = 425;BA.debugLine="If res(1).Trim <> Edt_PWD.Text.Trim Then";
if ((_res[(int) (1)].trim()).equals(mostCurrent._edt_pwd.getText().trim()) == false) { 
 //BA.debugLineNum = 426;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 427;BA.debugLine="Msgbox(\"Password Error\",\"login\")";
anywheresoftware.b4a.keywords.Common.Msgbox("Password Error","login",mostCurrent.activityBA);
 //BA.debugLineNum = 428;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 430;BA.debugLine="TitleTxt(\"功能列表\")";
_titletxt("功能列表");
 //BA.debugLineNum = 431;BA.debugLine="Edt_PWD.Text = \"\"";
mostCurrent._edt_pwd.setText((Object)(""));
 //BA.debugLineNum = 432;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 433;BA.debugLine="Str_Permit = res(2).ToLowerCase.Trim";
_str_permit = _res[(int) (2)].toLowerCase().trim();
 //BA.debugLineNum = 434;BA.debugLine="Lbl_User.Text = Edt_User.Text";
mostCurrent._lbl_user.setText((Object)(mostCurrent._edt_user.getText()));
 //BA.debugLineNum = 435;BA.debugLine="End Sub";
return "";
}
public static String  _btn_post_click() throws Exception{
String _sql = "";
int _i = 0;
 //BA.debugLineNum = 564;BA.debugLine="Sub Btn_Post_Click";
 //BA.debugLineNum = 565;BA.debugLine="ProgressDialogShow(\"正在提交中...\")";
anywheresoftware.b4a.keywords.Common.ProgressDialogShow(mostCurrent.activityBA,"正在提交中...");
 //BA.debugLineNum = 566;BA.debugLine="edt_arr(15).Text = Lbl_Time.Text";
mostCurrent._edt_arr[(int) (15)].setText((Object)(mostCurrent._lbl_time.getText()));
 //BA.debugLineNum = 568;BA.debugLine="Dim sql As String = \"\"";
_sql = "";
 //BA.debugLineNum = 569;BA.debugLine="For i = 0 To 2";
{
final int step435 = 1;
final int limit435 = (int) (2);
for (_i = (int) (0); (step435 > 0 && _i <= limit435) || (step435 < 0 && _i >= limit435); _i = ((int)(0 + _i + step435))) {
 //BA.debugLineNum = 570;BA.debugLine="sql = sql & \"'\" & edt_arr(i).Text & \"'\" & \",\"";
_sql = _sql+"'"+mostCurrent._edt_arr[_i].getText()+"'"+",";
 }
};
 //BA.debugLineNum = 572;BA.debugLine="sql = sql & \"'\" & Spn_Sequence.SelectedItem & \"'\"";
_sql = _sql+"'"+mostCurrent._spn_sequence.getSelectedItem()+"'"+",";
 //BA.debugLineNum = 573;BA.debugLine="sql = sql & \"'\" & Spn_InOutType.SelectedItem & \"'";
_sql = _sql+"'"+mostCurrent._spn_inouttype.getSelectedItem()+"'"+",";
 //BA.debugLineNum = 574;BA.debugLine="For i = 0 To 2";
{
final int step440 = 1;
final int limit440 = (int) (2);
for (_i = (int) (0); (step440 > 0 && _i <= limit440) || (step440 < 0 && _i >= limit440); _i = ((int)(0 + _i + step440))) {
 //BA.debugLineNum = 575;BA.debugLine="sql = sql & \"'\" & edt_arr(i + 13).Text & \"'\" & \"";
_sql = _sql+"'"+mostCurrent._edt_arr[(int) (_i+13)].getText()+"'"+",";
 }
};
 //BA.debugLineNum = 577;BA.debugLine="sql = sql & \"'\" & edt_arr(16).Text & \"'\"";
_sql = _sql+"'"+mostCurrent._edt_arr[(int) (16)].getText()+"'";
 //BA.debugLineNum = 578;BA.debugLine="sql = \"INSERT into dbo.ModuleOrder VALUES(\" & sql";
_sql = "INSERT into dbo.ModuleOrder VALUES("+_sql+")";
 //BA.debugLineNum = 579;BA.debugLine="Try";
try { //BA.debugLineNum = 580;BA.debugLine="db.ExecuteNonQuery(sql,True)";
_db.ExecuteNonQuery(_sql,anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 581;BA.debugLine="SubFunc.Waitms(1000)";
mostCurrent._subfunc._waitms(mostCurrent.activityBA,(int) (1000));
 //BA.debugLineNum = 582;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 } 
       catch (Exception e450) {
			processBA.setLastException(e450); //BA.debugLineNum = 584;BA.debugLine="SubFunc.Waitms(1000)";
mostCurrent._subfunc._waitms(mostCurrent.activityBA,(int) (1000));
 //BA.debugLineNum = 585;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 586;BA.debugLine="Msgbox(\"提交失败\",\"Insert\")";
anywheresoftware.b4a.keywords.Common.Msgbox("提交失败","Insert",mostCurrent.activityBA);
 //BA.debugLineNum = 587;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 589;BA.debugLine="Msgbox(\"提交成功\",\"Insert\")";
anywheresoftware.b4a.keywords.Common.Msgbox("提交成功","Insert",mostCurrent.activityBA);
 //BA.debugLineNum = 590;BA.debugLine="End Sub";
return "";
}
public static String  _btn_wificonnect_click() throws Exception{
long _ti = 0L;
 //BA.debugLineNum = 485;BA.debugLine="Sub Btn_WifiConnect_Click";
 //BA.debugLineNum = 486;BA.debugLine="ProgressDialogShow(\"正在连接\" & Spn_WifiList.Selected";
anywheresoftware.b4a.keywords.Common.ProgressDialogShow(mostCurrent.activityBA,"正在连接"+mostCurrent._spn_wifilist.getSelectedItem());
 //BA.debugLineNum = 487;BA.debugLine="SubFunc.Waitms(1000)";
mostCurrent._subfunc._waitms(mostCurrent.activityBA,(int) (1000));
 //BA.debugLineNum = 488;BA.debugLine="If Spn_WifiList.SelectedIndex - 1 <0 Then";
if (mostCurrent._spn_wifilist.getSelectedIndex()-1<0) { 
 //BA.debugLineNum = 489;BA.debugLine="Msgbox(\"无效wifi\",\"WifiConnect\")";
anywheresoftware.b4a.keywords.Common.Msgbox("无效wifi","WifiConnect",mostCurrent.activityBA);
 //BA.debugLineNum = 490;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 491;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 493;BA.debugLine="Try";
try { //BA.debugLineNum = 494;BA.debugLine="wifiscan.connectToAP(Spn_WifiList.SelectedIndex";
mostCurrent._wifiscan.connectToAP((int) (mostCurrent._spn_wifilist.getSelectedIndex()-1));
 } 
       catch (Exception e380) {
			processBA.setLastException(e380); //BA.debugLineNum = 496;BA.debugLine="Msgbox(\"连接错误，请重新扫描wifi\",\"WifiConnect\")";
anywheresoftware.b4a.keywords.Common.Msgbox("连接错误，请重新扫描wifi","WifiConnect",mostCurrent.activityBA);
 };
 //BA.debugLineNum = 498;BA.debugLine="Dim ti As Long";
_ti = 0L;
 //BA.debugLineNum = 500;BA.debugLine="ti = DateTime.Now + (15000)";
_ti = (long) (anywheresoftware.b4a.keywords.Common.DateTime.getNow()+(15000));
 //BA.debugLineNum = 501;BA.debugLine="Do While DateTime.Now < ti";
while (anywheresoftware.b4a.keywords.Common.DateTime.getNow()<_ti) {
 //BA.debugLineNum = 502;BA.debugLine="If wifi.SSID = Spn_WifiList.SelectedItem.SubStri";
if ((mostCurrent._wifi.SSID()).equals(mostCurrent._spn_wifilist.getSelectedItem().substring((int) (0),mostCurrent._spn_wifilist.getSelectedItem().indexOf(",")).trim()) && mostCurrent._wifi.isWifiConnected()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 504;BA.debugLine="Msgbox(\"连接成功\",\"WifiConnect\")";
anywheresoftware.b4a.keywords.Common.Msgbox("连接成功","WifiConnect",mostCurrent.activityBA);
 //BA.debugLineNum = 505;BA.debugLine="Pnl_WifiSet.Visible = False";
mostCurrent._pnl_wifiset.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 506;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 507;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 509;BA.debugLine="DoEvents";
anywheresoftware.b4a.keywords.Common.DoEvents();
 }
;
 //BA.debugLineNum = 511;BA.debugLine="ProgressDialogShow(\"连接指定wifi失败，自动连接中\")";
anywheresoftware.b4a.keywords.Common.ProgressDialogShow(mostCurrent.activityBA,"连接指定wifi失败，自动连接中");
 //BA.debugLineNum = 512;BA.debugLine="ti = DateTime.Now + (15000)";
_ti = (long) (anywheresoftware.b4a.keywords.Common.DateTime.getNow()+(15000));
 //BA.debugLineNum = 513;BA.debugLine="Do While DateTime.Now < ti";
while (anywheresoftware.b4a.keywords.Common.DateTime.getNow()<_ti) {
 //BA.debugLineNum = 514;BA.debugLine="If wifi.SSID <> \"\" And wifi.isWifiConnected = Tr";
if ((mostCurrent._wifi.SSID()).equals("") == false && mostCurrent._wifi.isWifiConnected()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 515;BA.debugLine="Msgbox(\"成功连接至：\" & wifi.SSID,\"WifiConnect\")";
anywheresoftware.b4a.keywords.Common.Msgbox("成功连接至："+mostCurrent._wifi.SSID(),"WifiConnect",mostCurrent.activityBA);
 //BA.debugLineNum = 516;BA.debugLine="Pnl_WifiSet.Visible = False";
mostCurrent._pnl_wifiset.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 517;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 518;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 520;BA.debugLine="DoEvents";
anywheresoftware.b4a.keywords.Common.DoEvents();
 }
;
 //BA.debugLineNum = 522;BA.debugLine="Msgbox(\"连接失败\",\"WifiConnect\")";
anywheresoftware.b4a.keywords.Common.Msgbox("连接失败","WifiConnect",mostCurrent.activityBA);
 //BA.debugLineNum = 523;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 524;BA.debugLine="End Sub";
return "";
}
public static String  _disablestrictmode() throws Exception{
anywheresoftware.b4j.object.JavaObject _jo = null;
anywheresoftware.b4j.object.JavaObject _policy = null;
anywheresoftware.b4j.object.JavaObject _sm = null;
 //BA.debugLineNum = 126;BA.debugLine="Sub DisableStrictMode";
 //BA.debugLineNum = 127;BA.debugLine="Dim jo As JavaObject";
_jo = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 128;BA.debugLine="jo.InitializeStatic(\"android.os.Build.VERSION\")";
_jo.InitializeStatic("android.os.Build.VERSION");
 //BA.debugLineNum = 129;BA.debugLine="If jo.GetField(\"SDK_INT\") > 9 Then";
if ((double)(BA.ObjectToNumber(_jo.GetField("SDK_INT")))>9) { 
 //BA.debugLineNum = 130;BA.debugLine="Dim policy As JavaObject";
_policy = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 131;BA.debugLine="policy = policy.InitializeNewInstance(\"androi";
_policy = _policy.InitializeNewInstance("android.os.StrictMode.ThreadPolicy.Builder",(Object[])(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 132;BA.debugLine="policy = policy.RunMethodJO(\"permitAll\", Null";
_policy = _policy.RunMethodJO("permitAll",(Object[])(anywheresoftware.b4a.keywords.Common.Null)).RunMethodJO("build",(Object[])(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 133;BA.debugLine="Dim sm As JavaObject";
_sm = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 134;BA.debugLine="sm.InitializeStatic(\"android.os.StrictMode\").";
_sm.InitializeStatic("android.os.StrictMode").RunMethod("setThreadPolicy",new Object[]{(Object)(_policy.getObject())});
 };
 //BA.debugLineNum = 136;BA.debugLine="End Sub";
return "";
}
public static String  _edt_arr0_enterpressed() throws Exception{
 //BA.debugLineNum = 620;BA.debugLine="Sub edt_arr0_EnterPressed";
 //BA.debugLineNum = 621;BA.debugLine="OrderSelect(edt_arr(0).Text.Trim)";
_orderselect(mostCurrent._edt_arr[(int) (0)].getText().trim());
 //BA.debugLineNum = 622;BA.debugLine="End Sub";
return "";
}
public static String  _expandcollapsestatusbar(boolean _choice) throws Exception{
anywheresoftware.b4a.agraham.reflection.Reflection _r = null;
 //BA.debugLineNum = 143;BA.debugLine="Sub expandCollapseStatusBar(choice As Boolean )";
 //BA.debugLineNum = 144;BA.debugLine="Dim r As Reflector";
_r = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 145;BA.debugLine="r.Target =r.GetContext";
_r.Target = (Object)(_r.GetContext(processBA));
 //BA.debugLineNum = 146;BA.debugLine="r.target=r.RunMethod2(\"getSystemService\",\"stat";
_r.Target = _r.RunMethod2("getSystemService","statusbar","java.lang.String");
 //BA.debugLineNum = 147;BA.debugLine="If choice Then";
if (_choice) { 
 //BA.debugLineNum = 148;BA.debugLine="r.RunMethod (\"expandNotificationsPanel\")";
_r.RunMethod("expandNotificationsPanel");
 }else {
 //BA.debugLineNum = 150;BA.debugLine="r.RunMethod (\"collapsePanels\")";
_r.RunMethod("collapsePanels");
 };
 //BA.debugLineNum = 152;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4j.object.JavaObject  _getba() throws Exception{
anywheresoftware.b4j.object.JavaObject _jo = null;
String _cls = "";
 //BA.debugLineNum = 185;BA.debugLine="Sub GetBA As JavaObject";
 //BA.debugLineNum = 186;BA.debugLine="Dim jo As JavaObject";
_jo = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 187;BA.debugLine="Dim cls As String = Me";
_cls = BA.ObjectToString(main.getObject());
 //BA.debugLineNum = 188;BA.debugLine="cls = cls.SubString(\"class \".Length)";
_cls = _cls.substring("class ".length());
 //BA.debugLineNum = 189;BA.debugLine="jo.InitializeStatic(cls)";
_jo.InitializeStatic(_cls);
 //BA.debugLineNum = 190;BA.debugLine="Return jo.GetFieldJO(\"processBA\")";
if (true) return _jo.GetFieldJO("processBA");
 //BA.debugLineNum = 191;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4j.object.JavaObject  _getcontext() throws Exception{
 //BA.debugLineNum = 181;BA.debugLine="Sub GetContext As JavaObject";
 //BA.debugLineNum = 182;BA.debugLine="Return GetBA.GetField(\"context\")";
if (true) return (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(_getba().GetField("context")));
 //BA.debugLineNum = 183;BA.debugLine="End Sub";
return null;
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 27;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 30;BA.debugLine="Private Btn_Login As Button";
mostCurrent._btn_login = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 31;BA.debugLine="Private Edt_PWD As EditText";
mostCurrent._edt_pwd = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 32;BA.debugLine="Private Edt_User As EditText";
mostCurrent._edt_user = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 33;BA.debugLine="Private Pnl_Login As Panel";
mostCurrent._pnl_login = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 35;BA.debugLine="Dim scrview(20) As ScrollView";
mostCurrent._scrview = new anywheresoftware.b4a.objects.ScrollViewWrapper[(int) (20)];
{
int d0 = mostCurrent._scrview.length;
for (int i0 = 0;i0 < d0;i0++) {
mostCurrent._scrview[i0] = new anywheresoftware.b4a.objects.ScrollViewWrapper();
}
}
;
 //BA.debugLineNum = 37;BA.debugLine="Dim lbl_arr(30) As Label";
mostCurrent._lbl_arr = new anywheresoftware.b4a.objects.LabelWrapper[(int) (30)];
{
int d0 = mostCurrent._lbl_arr.length;
for (int i0 = 0;i0 < d0;i0++) {
mostCurrent._lbl_arr[i0] = new anywheresoftware.b4a.objects.LabelWrapper();
}
}
;
 //BA.debugLineNum = 38;BA.debugLine="Dim lbl_txt(30) As String";
mostCurrent._lbl_txt = new String[(int) (30)];
java.util.Arrays.fill(mostCurrent._lbl_txt,"");
 //BA.debugLineNum = 39;BA.debugLine="Dim edt_arr(30) As EditText";
mostCurrent._edt_arr = new anywheresoftware.b4a.objects.EditTextWrapper[(int) (30)];
{
int d0 = mostCurrent._edt_arr.length;
for (int i0 = 0;i0 < d0;i0++) {
mostCurrent._edt_arr[i0] = new anywheresoftware.b4a.objects.EditTextWrapper();
}
}
;
 //BA.debugLineNum = 41;BA.debugLine="Dim Spn_Sequence As Spinner";
mostCurrent._spn_sequence = new anywheresoftware.b4a.objects.SpinnerWrapper();
 //BA.debugLineNum = 42;BA.debugLine="Dim Spn_InOutType As Spinner";
mostCurrent._spn_inouttype = new anywheresoftware.b4a.objects.SpinnerWrapper();
 //BA.debugLineNum = 44;BA.debugLine="Dim Btn_Post As Button";
mostCurrent._btn_post = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 46;BA.debugLine="Dim ime As IME";
mostCurrent._ime = new anywheresoftware.b4a.objects.IME();
 //BA.debugLineNum = 47;BA.debugLine="Dim zx As JhsIceZxing1";
mostCurrent._zx = new ice.zxing.b4aZXingLib();
 //BA.debugLineNum = 49;BA.debugLine="Private Pnl_Title As Panel";
mostCurrent._pnl_title = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 50;BA.debugLine="Private Lbl_Action As Label";
mostCurrent._lbl_action = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 51;BA.debugLine="Private Lbl_Set As Label";
mostCurrent._lbl_set = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 52;BA.debugLine="Private Lbl_Title As Label";
mostCurrent._lbl_title = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 53;BA.debugLine="Private Lbl_Time As Label";
mostCurrent._lbl_time = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 54;BA.debugLine="Private Lbl_User As Label";
mostCurrent._lbl_user = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 55;BA.debugLine="Private Lbl_Status As Label";
mostCurrent._lbl_status = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 57;BA.debugLine="Dim Lst_Func As ListView";
mostCurrent._lst_func = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 58;BA.debugLine="Dim Lst_Set As ListView";
mostCurrent._lst_set = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 60;BA.debugLine="Dim wifi As MLwifi";
mostCurrent._wifi = new wifi.MLwifi();
 //BA.debugLineNum = 61;BA.debugLine="Dim wifiscan As MLScan";
mostCurrent._wifiscan = new wifi.MLwifi.MLWifiScanner();
 //BA.debugLineNum = 62;BA.debugLine="Dim Pnl_WifiSet As Panel";
mostCurrent._pnl_wifiset = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 63;BA.debugLine="Dim Spn_WifiList As Spinner";
mostCurrent._spn_wifilist = new anywheresoftware.b4a.objects.SpinnerWrapper();
 //BA.debugLineNum = 64;BA.debugLine="Dim Btn_WifiConnect As Button";
mostCurrent._btn_wificonnect = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 65;BA.debugLine="End Sub";
return "";
}
public static String  _ime_heightchanged(int _newheight,int _oldheight) throws Exception{
 //BA.debugLineNum = 625;BA.debugLine="Sub ime_HeightChanged (NewHeight As Int, OldHeight";
 //BA.debugLineNum = 626;BA.debugLine="If NewHeight < OldHeight Then";
if (_newheight<_oldheight) { 
 //BA.debugLineNum = 627;BA.debugLine="scrview(0).Height = 88%y - OldHeight + NewHeight";
mostCurrent._scrview[(int) (0)].setHeight((int) (anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (88),mostCurrent.activityBA)-_oldheight+_newheight+anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (7),mostCurrent.activityBA)));
 }else {
 //BA.debugLineNum = 629;BA.debugLine="scrview(0).Height = 88%y";
mostCurrent._scrview[(int) (0)].setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (88),mostCurrent.activityBA));
 };
 //BA.debugLineNum = 631;BA.debugLine="End Sub";
return "";
}
public static String  _lbl_action_click() throws Exception{
 //BA.debugLineNum = 385;BA.debugLine="Sub Lbl_Action_Click";
 //BA.debugLineNum = 386;BA.debugLine="Select Lbl_Title.Text";
switch (BA.switchObjectToInt(mostCurrent._lbl_title.getText(),"用户登录","功能列表","IGBT模块随工单","设置")) {
case 0:
 break;
case 1:
 //BA.debugLineNum = 390;BA.debugLine="If Msgbox2(\"Exit?\",\"flow\",\"yes\",\"no\",\"\",Null) =";
if (anywheresoftware.b4a.keywords.Common.Msgbox2("Exit?","flow","yes","no","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null),mostCurrent.activityBA)==anywheresoftware.b4a.keywords.Common.DialogResponse.CANCEL) { 
if (true) return "";};
 //BA.debugLineNum = 391;BA.debugLine="Lbl_Title.Text = \"用户登录\"";
mostCurrent._lbl_title.setText((Object)("用户登录"));
 //BA.debugLineNum = 392;BA.debugLine="Str_Permit = \"\"";
_str_permit = "";
 //BA.debugLineNum = 393;BA.debugLine="Lbl_User.Text = \"\"";
mostCurrent._lbl_user.setText((Object)(""));
 break;
case 2:
 //BA.debugLineNum = 396;BA.debugLine="Lbl_Title.Text = \"功能列表\"";
mostCurrent._lbl_title.setText((Object)("功能列表"));
 break;
case 3:
 //BA.debugLineNum = 399;BA.debugLine="Lbl_Title.Text = str_setbuff";
mostCurrent._lbl_title.setText((Object)(_str_setbuff));
 break;
}
;
 //BA.debugLineNum = 401;BA.debugLine="TitleTxt(Lbl_Title.Text)";
_titletxt(mostCurrent._lbl_title.getText());
 //BA.debugLineNum = 402;BA.debugLine="End Sub";
return "";
}
public static String  _lbl_arr0_click() throws Exception{
 //BA.debugLineNum = 528;BA.debugLine="Sub lbl_arr0_Click";
 //BA.debugLineNum = 529;BA.debugLine="zx.isportrait = True";
mostCurrent._zx.isportrait = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 530;BA.debugLine="zx.useFrontCam = False";
mostCurrent._zx.useFrontCam = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 534;BA.debugLine="zx.theViewFinderXfactor = 0.7";
mostCurrent._zx.theViewFinderXfactor = 0.7;
 //BA.debugLineNum = 535;BA.debugLine="zx.theViewFinderYfactor = 0.5";
mostCurrent._zx.theViewFinderYfactor = 0.5;
 //BA.debugLineNum = 537;BA.debugLine="zx.theFrameColor = Colors.Blue";
mostCurrent._zx.theFrameColor = anywheresoftware.b4a.keywords.Common.Colors.Blue;
 //BA.debugLineNum = 538;BA.debugLine="zx.theLaserColor = Colors.Yellow";
mostCurrent._zx.theLaserColor = anywheresoftware.b4a.keywords.Common.Colors.Yellow;
 //BA.debugLineNum = 539;BA.debugLine="zx.theMaskColor = Colors.argb(95, 0, 0, 255)";
mostCurrent._zx.theMaskColor = anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (95),(int) (0),(int) (0),(int) (255));
 //BA.debugLineNum = 540;BA.debugLine="zx.theResultColor = Colors.Green";
mostCurrent._zx.theResultColor = anywheresoftware.b4a.keywords.Common.Colors.Green;
 //BA.debugLineNum = 541;BA.debugLine="zx.theResultPointColor = Colors.White";
mostCurrent._zx.theResultPointColor = anywheresoftware.b4a.keywords.Common.Colors.White;
 //BA.debugLineNum = 544;BA.debugLine="zx.theTopPromptMessage = \"条码识别\"";
mostCurrent._zx.theTopPromptMessage = "条码识别";
 //BA.debugLineNum = 545;BA.debugLine="zx.theTopPromptTextSize = 5%y";
mostCurrent._zx.theTopPromptTextSize = anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (5),mostCurrent.activityBA);
 //BA.debugLineNum = 546;BA.debugLine="zx.topPromptColor = Colors.Red";
mostCurrent._zx.topPromptColor = anywheresoftware.b4a.keywords.Common.Colors.Red;
 //BA.debugLineNum = 547;BA.debugLine="zx.topPromptDistanceFromTop = 7%y";
mostCurrent._zx.topPromptDistanceFromTop = anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (7),mostCurrent.activityBA);
 //BA.debugLineNum = 549;BA.debugLine="zx.theBottomPromptMessage = \"单击切换闪光灯\"";
mostCurrent._zx.theBottomPromptMessage = "单击切换闪光灯";
 //BA.debugLineNum = 550;BA.debugLine="zx.theBottomPromptTextSize = 5%y";
mostCurrent._zx.theBottomPromptTextSize = anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (5),mostCurrent.activityBA);
 //BA.debugLineNum = 551;BA.debugLine="zx.bottomPromptColor = Colors.Blue";
mostCurrent._zx.bottomPromptColor = anywheresoftware.b4a.keywords.Common.Colors.Blue;
 //BA.debugLineNum = 552;BA.debugLine="zx.bottomPromptDistanceFromBottom = 7%y";
mostCurrent._zx.bottomPromptDistanceFromBottom = anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (7),mostCurrent.activityBA);
 //BA.debugLineNum = 554;BA.debugLine="zx.BeginScan(\"myzx\")";
mostCurrent._zx.BeginScan(mostCurrent.activityBA,"myzx");
 //BA.debugLineNum = 555;BA.debugLine="End Sub";
return "";
}
public static String  _lbl_set_click() throws Exception{
 //BA.debugLineNum = 375;BA.debugLine="Sub Lbl_Set_Click";
 //BA.debugLineNum = 376;BA.debugLine="If Edt_User.Text.Trim = \"Admin\" And Edt_PWD.Text.";
if ((mostCurrent._edt_user.getText().trim()).equals("Admin") && (mostCurrent._edt_pwd.getText().trim()).equals("BYD") || (_str_permit).equals("") == false) { 
 //BA.debugLineNum = 377;BA.debugLine="str_setbuff = Lbl_Title.Text";
_str_setbuff = mostCurrent._lbl_title.getText();
 //BA.debugLineNum = 378;BA.debugLine="TitleTxt(\"设置\")";
_titletxt("设置");
 }else {
 //BA.debugLineNum = 380;BA.debugLine="Msgbox(\"无操作权限\",\"FlowCollect\")";
anywheresoftware.b4a.keywords.Common.Msgbox("无操作权限","FlowCollect",mostCurrent.activityBA);
 //BA.debugLineNum = 381;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 383;BA.debugLine="End Sub";
return "";
}
public static String  _lst_func_itemclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 438;BA.debugLine="Sub lst_func_ItemClick (Position As Int, Value As";
 //BA.debugLineNum = 439;BA.debugLine="Select Position";
switch (_position) {
case 0:
 //BA.debugLineNum = 441;BA.debugLine="TitleTxt(\"IGBT模块随工单\")";
_titletxt("IGBT模块随工单");
 //BA.debugLineNum = 442;BA.debugLine="edt_arr(SubFunc.ArraySearch(lbl_txt,\"作业员\")).Tex";
mostCurrent._edt_arr[mostCurrent._subfunc._arraysearch(mostCurrent.activityBA,mostCurrent._lbl_txt,"作业员")].setText((Object)(mostCurrent._edt_user.getText()));
 break;
}
;
 //BA.debugLineNum = 444;BA.debugLine="End Sub";
return "";
}
public static String  _lst_set_itemclick(int _position,Object _value) throws Exception{
anywheresoftware.b4a.objects.IntentWrapper _i = null;
 //BA.debugLineNum = 447;BA.debugLine="Sub lst_set_ItemClick (Position As Int, Value As O";
 //BA.debugLineNum = 448;BA.debugLine="Pnl_WifiSet.Visible = False";
mostCurrent._pnl_wifiset.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 449;BA.debugLine="Select Lst_Set.GetItem(Position)";
switch (BA.switchObjectToInt(mostCurrent._lst_set.GetItem(_position),(Object)("无线网络设置"),(Object)("系统参数设置"))) {
case 0:
 //BA.debugLineNum = 451;BA.debugLine="Pnl_WifiSet.Visible = True";
mostCurrent._pnl_wifiset.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 452;BA.debugLine="ProgressDialogShow(\"正在扫描wifi\")";
anywheresoftware.b4a.keywords.Common.ProgressDialogShow(mostCurrent.activityBA,"正在扫描wifi");
 //BA.debugLineNum = 453;BA.debugLine="wifiscan.startScan(\"wifiscan\")";
mostCurrent._wifiscan.startScan(processBA,"wifiscan");
 break;
case 1:
 //BA.debugLineNum = 455;BA.debugLine="If Str_Permit = \"admin\" Then";
if ((_str_permit).equals("admin")) { 
 //BA.debugLineNum = 456;BA.debugLine="Dim i As Intent";
_i = new anywheresoftware.b4a.objects.IntentWrapper();
 //BA.debugLineNum = 457;BA.debugLine="i.Initialize(\"\", \"\")";
_i.Initialize("","");
 //BA.debugLineNum = 458;BA.debugLine="i.SetComponent(\"com.android.settings/.Settings";
_i.SetComponent("com.android.settings/.Settings");
 //BA.debugLineNum = 459;BA.debugLine="StartActivity(i)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(_i.getObject()));
 }else {
 //BA.debugLineNum = 461;BA.debugLine="Msgbox(\"无操作权限\",\"SystemSet\")";
anywheresoftware.b4a.keywords.Common.Msgbox("无操作权限","SystemSet",mostCurrent.activityBA);
 };
 break;
}
;
 //BA.debugLineNum = 464;BA.debugLine="End Sub";
return "";
}
public static String  _myzx_result(String _atype,String _values) throws Exception{
 //BA.debugLineNum = 557;BA.debugLine="Sub myzx_result(atype As String,Values As String)";
 //BA.debugLineNum = 558;BA.debugLine="edt_arr(0).Text = Values";
mostCurrent._edt_arr[(int) (0)].setText((Object)(_values));
 //BA.debugLineNum = 559;BA.debugLine="OrderSelect(Values)";
_orderselect(_values);
 //BA.debugLineNum = 560;BA.debugLine="End Sub";
return "";
}
public static String  _orderselect(String _order) throws Exception{
String _s = "";
String[] _res = null;
int _i = 0;
 //BA.debugLineNum = 592;BA.debugLine="Sub OrderSelect(order As String)";
 //BA.debugLineNum = 593;BA.debugLine="edt_arr(SubFunc.ArraySearch(lbl_txt,\"作业时间\")).Text";
mostCurrent._edt_arr[mostCurrent._subfunc._arraysearch(mostCurrent.activityBA,mostCurrent._lbl_txt,"作业时间")].setText((Object)(mostCurrent._lbl_time.getText()));
 //BA.debugLineNum = 594;BA.debugLine="ProgressDialogShow(\"正在查询中...\")";
anywheresoftware.b4a.keywords.Common.ProgressDialogShow(mostCurrent.activityBA,"正在查询中...");
 //BA.debugLineNum = 595;BA.debugLine="Try";
try { //BA.debugLineNum = 596;BA.debugLine="lst_result = db.Query(\"SELECT Product,BatchNum,I";
_lst_result.setObject((java.util.List)(_db.Query("SELECT Product,BatchNum,IDNum,TotalNum,OrderType,OrderLevel,"+"Number,StartEnd FROM dbo.OrderPlan WHERE [Order] = '"+_order+"'")));
 //BA.debugLineNum = 598;BA.debugLine="SubFunc.Waitms(1000)";
mostCurrent._subfunc._waitms(mostCurrent.activityBA,(int) (1000));
 } 
       catch (Exception e464) {
			processBA.setLastException(e464); //BA.debugLineNum = 600;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 601;BA.debugLine="Msgbox(\"Server Error\",\"select\")";
anywheresoftware.b4a.keywords.Common.Msgbox("Server Error","select",mostCurrent.activityBA);
 //BA.debugLineNum = 602;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 604;BA.debugLine="If lst_result.Size < 2 Then";
if (_lst_result.getSize()<2) { 
 //BA.debugLineNum = 605;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 606;BA.debugLine="Msgbox(\"Order error\",\"select\")";
anywheresoftware.b4a.keywords.Common.Msgbox("Order error","select",mostCurrent.activityBA);
 //BA.debugLineNum = 607;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 609;BA.debugLine="Dim s As String";
_s = "";
 //BA.debugLineNum = 610;BA.debugLine="s = lst_result.Get(1)";
_s = BA.ObjectToString(_lst_result.Get((int) (1)));
 //BA.debugLineNum = 611;BA.debugLine="s = s.SubString2(s.IndexOf(\"[\") + 1, s.IndexOf(\"]";
_s = _s.substring((int) (_s.indexOf("[")+1),_s.indexOf("]")).trim();
 //BA.debugLineNum = 612;BA.debugLine="Dim res() As String";
_res = new String[(int) (0)];
java.util.Arrays.fill(_res,"");
 //BA.debugLineNum = 613;BA.debugLine="res = Regex.Split(\",\",s)";
_res = anywheresoftware.b4a.keywords.Common.Regex.Split(",",_s);
 //BA.debugLineNum = 614;BA.debugLine="For i = 0 To 7";
{
final int step478 = 1;
final int limit478 = (int) (7);
for (_i = (int) (0); (step478 > 0 && _i <= limit478) || (step478 < 0 && _i >= limit478); _i = ((int)(0 + _i + step478))) {
 //BA.debugLineNum = 615;BA.debugLine="edt_arr(3+i).Text = res(i).Trim";
mostCurrent._edt_arr[(int) (3+_i)].setText((Object)(_res[_i].trim()));
 }
};
 //BA.debugLineNum = 617;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 618;BA.debugLine="End Sub";
return "";
}
public static String  _panelset() throws Exception{
int _i = 0;
 //BA.debugLineNum = 194;BA.debugLine="Sub PanelSet";
 //BA.debugLineNum = 196;BA.debugLine="Lbl_Action.Text = \"\"";
mostCurrent._lbl_action.setText((Object)(""));
 //BA.debugLineNum = 197;BA.debugLine="Lbl_Title.Text = \"用户登录\"";
mostCurrent._lbl_title.setText((Object)("用户登录"));
 //BA.debugLineNum = 198;BA.debugLine="Lbl_Set.Text = \"设置\"";
mostCurrent._lbl_set.setText((Object)("设置"));
 //BA.debugLineNum = 199;BA.debugLine="Lbl_Action.Gravity = Gravity.CENTER";
mostCurrent._lbl_action.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 200;BA.debugLine="Lbl_Title.Gravity = Gravity.CENTER";
mostCurrent._lbl_title.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 201;BA.debugLine="Lbl_Set.Gravity = Gravity.CENTER";
mostCurrent._lbl_set.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 202;BA.debugLine="Lbl_Action.TextColor = Colors.Black";
mostCurrent._lbl_action.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 203;BA.debugLine="Lbl_Title.TextColor = Colors.Black";
mostCurrent._lbl_title.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 204;BA.debugLine="Lbl_Set.TextColor = Colors.Black";
mostCurrent._lbl_set.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 207;BA.debugLine="Lbl_Time.Gravity = Gravity.CENTER";
mostCurrent._lbl_time.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 208;BA.debugLine="Lbl_Time.TextColor = Colors.Black";
mostCurrent._lbl_time.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 209;BA.debugLine="Lbl_Time.Color = Colors.RGB(0xF4,0x3A,0x3A)";
mostCurrent._lbl_time.setColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (0xf4),(int) (0x3a),(int) (0x3a)));
 //BA.debugLineNum = 210;BA.debugLine="Lbl_User.Gravity = Gravity.CENTER_VERTICAL + Grav";
mostCurrent._lbl_user.setGravity((int) (anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL+anywheresoftware.b4a.keywords.Common.Gravity.LEFT));
 //BA.debugLineNum = 211;BA.debugLine="Lbl_User.TextColor = Colors.Black";
mostCurrent._lbl_user.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 212;BA.debugLine="Lbl_User.Color = Colors.RGB(0xF4,0x3A,0x3A)";
mostCurrent._lbl_user.setColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (0xf4),(int) (0x3a),(int) (0x3a)));
 //BA.debugLineNum = 213;BA.debugLine="Lbl_Status.Gravity = Gravity.CENTER_VERTICAL + Gr";
mostCurrent._lbl_status.setGravity((int) (anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL+anywheresoftware.b4a.keywords.Common.Gravity.RIGHT));
 //BA.debugLineNum = 214;BA.debugLine="Lbl_Status.TextColor = Colors.Black";
mostCurrent._lbl_status.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 215;BA.debugLine="Lbl_Status.Color = Colors.RGB(0xF4,0x3A,0x3A)";
mostCurrent._lbl_status.setColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (0xf4),(int) (0x3a),(int) (0x3a)));
 //BA.debugLineNum = 218;BA.debugLine="Edt_User.Color = Colors.Gray";
mostCurrent._edt_user.setColor(anywheresoftware.b4a.keywords.Common.Colors.Gray);
 //BA.debugLineNum = 219;BA.debugLine="Edt_User.Hint = \"请输入用户名\"";
mostCurrent._edt_user.setHint("请输入用户名");
 //BA.debugLineNum = 220;BA.debugLine="Edt_User.HintColor = Colors.White";
mostCurrent._edt_user.setHintColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 221;BA.debugLine="Edt_PWD.Color = Colors.Gray";
mostCurrent._edt_pwd.setColor(anywheresoftware.b4a.keywords.Common.Colors.Gray);
 //BA.debugLineNum = 222;BA.debugLine="Edt_PWD.Hint = \"请输入密码\"";
mostCurrent._edt_pwd.setHint("请输入密码");
 //BA.debugLineNum = 223;BA.debugLine="Edt_PWD.HintColor = Colors.White";
mostCurrent._edt_pwd.setHintColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 224;BA.debugLine="Edt_PWD.PasswordMode = True";
mostCurrent._edt_pwd.setPasswordMode(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 225;BA.debugLine="Btn_Login.Text = \"Login\"";
mostCurrent._btn_login.setText((Object)("Login"));
 //BA.debugLineNum = 228;BA.debugLine="Lst_Func.Initialize(\"Lst_Func\")";
mostCurrent._lst_func.Initialize(mostCurrent.activityBA,"Lst_Func");
 //BA.debugLineNum = 229;BA.debugLine="Activity.AddView(Lst_Func,0,7%y,100%x,88%y)";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._lst_func.getObject()),(int) (0),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (7),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (88),mostCurrent.activityBA));
 //BA.debugLineNum = 230;BA.debugLine="Lst_Func.Color = Colors.LightGray";
mostCurrent._lst_func.setColor(anywheresoftware.b4a.keywords.Common.Colors.LightGray);
 //BA.debugLineNum = 231;BA.debugLine="Lst_Func.AddSingleLine(\"IGBT 模块随工单\")";
mostCurrent._lst_func.AddSingleLine("IGBT 模块随工单");
 //BA.debugLineNum = 232;BA.debugLine="Lst_Func.AddSingleLine(\"IGBT DBC随工单\")";
mostCurrent._lst_func.AddSingleLine("IGBT DBC随工单");
 //BA.debugLineNum = 233;BA.debugLine="Lst_Func.AddSingleLine(\"IGBT 工单查询\")";
mostCurrent._lst_func.AddSingleLine("IGBT 工单查询");
 //BA.debugLineNum = 234;BA.debugLine="Lst_Func.Visible = False";
mostCurrent._lst_func.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 237;BA.debugLine="scrview(0).Initialize(100%y)";
mostCurrent._scrview[(int) (0)].Initialize(mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA));
 //BA.debugLineNum = 238;BA.debugLine="Activity.AddView(scrview(0),0,7%y,100%x,88%y)";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._scrview[(int) (0)].getObject()),(int) (0),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (7),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (88),mostCurrent.activityBA));
 //BA.debugLineNum = 239;BA.debugLine="scrview(0).Visible = False";
mostCurrent._scrview[(int) (0)].setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 240;BA.debugLine="scrview(0).Color = Colors.LightGray";
mostCurrent._scrview[(int) (0)].setColor(anywheresoftware.b4a.keywords.Common.Colors.LightGray);
 //BA.debugLineNum = 241;BA.debugLine="lbl_txt = Array As String(\"工单(单击扫描)\",\"作业员\",\"机台编号\"";
mostCurrent._lbl_txt = new String[]{"工单(单击扫描)","作业员","机台编号","产品型号","产品批号","订单编号","订单总量","订单类型","订单等级","本单数量","起始模块编号","工序名称","进出类型","接料数量","加入数量","作业时间","备注"};
 //BA.debugLineNum = 244;BA.debugLine="For i = 0 To lbl_txt.Length - 1";
{
final int step167 = 1;
final int limit167 = (int) (mostCurrent._lbl_txt.length-1);
for (_i = (int) (0); (step167 > 0 && _i <= limit167) || (step167 < 0 && _i >= limit167); _i = ((int)(0 + _i + step167))) {
 //BA.debugLineNum = 245;BA.debugLine="lbl_arr(i).Initialize(\"lbl_arr\" & i)";
mostCurrent._lbl_arr[_i].Initialize(mostCurrent.activityBA,"lbl_arr"+BA.NumberToString(_i));
 //BA.debugLineNum = 246;BA.debugLine="scrview(0).Panel.AddView(lbl_arr(i),1%y,2%y +12%";
mostCurrent._scrview[(int) (0)].getPanel().AddView((android.view.View)(mostCurrent._lbl_arr[_i].getObject()),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (1),mostCurrent.activityBA),(int) (anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (2),mostCurrent.activityBA)+anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (12),mostCurrent.activityBA)*_i),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (35),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (10),mostCurrent.activityBA));
 //BA.debugLineNum = 247;BA.debugLine="lbl_arr(i).Text = lbl_txt(i)";
mostCurrent._lbl_arr[_i].setText((Object)(mostCurrent._lbl_txt[_i]));
 //BA.debugLineNum = 248;BA.debugLine="lbl_arr(i).TextColor = Colors.Black";
mostCurrent._lbl_arr[_i].setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 249;BA.debugLine="Select i";
switch (_i) {
case 11:
 //BA.debugLineNum = 251;BA.debugLine="Spn_Sequence.Initialize(\"\")";
mostCurrent._spn_sequence.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 252;BA.debugLine="scrview(0).Panel.AddView(Spn_Sequence,35%x,0 +";
mostCurrent._scrview[(int) (0)].getPanel().AddView((android.view.View)(mostCurrent._spn_sequence.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (35),mostCurrent.activityBA),(int) (0+anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (12),mostCurrent.activityBA)*_i),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (60),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (10),mostCurrent.activityBA));
 //BA.debugLineNum = 253;BA.debugLine="Spn_Sequence.AddAll(Array As String(\"二次焊接\",\"超声";
mostCurrent._spn_sequence.AddAll(anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{"二次焊接","超声波扫描","模块清洗","模块烘烤","二次焊接全检","半成品测试","涂胶","密封胶固化","二次绑线","三次绑线","三次绑线全检","三次绑线抽检","灌胶","硅凝胶固化","装盖板(s)","模块打标","振动筛选","高温反偏","绝缘测试","外观清理","成品终测","弧度测试","生产终检","FQC抽检","数据核对"}));
 //BA.debugLineNum = 258;BA.debugLine="Spn_Sequence.Color = Colors.Gray";
mostCurrent._spn_sequence.setColor(anywheresoftware.b4a.keywords.Common.Colors.Gray);
 break;
case 12:
 //BA.debugLineNum = 260;BA.debugLine="Spn_InOutType.Initialize(\"\")";
mostCurrent._spn_inouttype.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 261;BA.debugLine="scrview(0).Panel.AddView(Spn_InOutType,35%x,1%";
mostCurrent._scrview[(int) (0)].getPanel().AddView((android.view.View)(mostCurrent._spn_inouttype.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (35),mostCurrent.activityBA),(int) (anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (1),mostCurrent.activityBA)+anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (12),mostCurrent.activityBA)*_i),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (60),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (10),mostCurrent.activityBA));
 //BA.debugLineNum = 262;BA.debugLine="Spn_InOutType.AddAll(Array As String(\"进料\",\"出料\"";
mostCurrent._spn_inouttype.AddAll(anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{"进料","出料"}));
 //BA.debugLineNum = 263;BA.debugLine="Spn_InOutType.Color = Colors.Gray";
mostCurrent._spn_inouttype.setColor(anywheresoftware.b4a.keywords.Common.Colors.Gray);
 break;
default:
 //BA.debugLineNum = 265;BA.debugLine="edt_arr(i).Initialize(\"edt_arr\" & i)";
mostCurrent._edt_arr[_i].Initialize(mostCurrent.activityBA,"edt_arr"+BA.NumberToString(_i));
 //BA.debugLineNum = 266;BA.debugLine="If i >=3 And i<=10 Then edt_arr(i).Enabled = F";
if (_i>=3 && _i<=10) { 
mostCurrent._edt_arr[_i].setEnabled(anywheresoftware.b4a.keywords.Common.False);};
 //BA.debugLineNum = 267;BA.debugLine="If i = 13 Or i = 14 Then edt_arr(i).InputType";
if (_i==13 || _i==14) { 
mostCurrent._edt_arr[_i].setInputType(mostCurrent._edt_arr[_i].INPUT_TYPE_NUMBERS);};
 //BA.debugLineNum = 268;BA.debugLine="If i = 15 Then edt_arr(i).Enabled = False";
if (_i==15) { 
mostCurrent._edt_arr[_i].setEnabled(anywheresoftware.b4a.keywords.Common.False);};
 //BA.debugLineNum = 269;BA.debugLine="If i = lbl_txt.Length - 1 Then";
if (_i==mostCurrent._lbl_txt.length-1) { 
 //BA.debugLineNum = 270;BA.debugLine="scrview(0).Panel.AddView(edt_arr(i),35%x,1%y";
mostCurrent._scrview[(int) (0)].getPanel().AddView((android.view.View)(mostCurrent._edt_arr[_i].getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (35),mostCurrent.activityBA),(int) (anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (1),mostCurrent.activityBA)+anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (12),mostCurrent.activityBA)*_i),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (60),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (30),mostCurrent.activityBA));
 }else {
 //BA.debugLineNum = 272;BA.debugLine="scrview(0).Panel.AddView(edt_arr(i),35%x,1%y";
mostCurrent._scrview[(int) (0)].getPanel().AddView((android.view.View)(mostCurrent._edt_arr[_i].getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (35),mostCurrent.activityBA),(int) (anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (1),mostCurrent.activityBA)+anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (12),mostCurrent.activityBA)*_i),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (60),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (10),mostCurrent.activityBA));
 };
 //BA.debugLineNum = 274;BA.debugLine="edt_arr(i).Color = Colors.Gray";
mostCurrent._edt_arr[_i].setColor(anywheresoftware.b4a.keywords.Common.Colors.Gray);
 //BA.debugLineNum = 275;BA.debugLine="edt_arr(i).Tag = \"edt_arr\" & i";
mostCurrent._edt_arr[_i].setTag((Object)("edt_arr"+BA.NumberToString(_i)));
 break;
}
;
 }
};
 //BA.debugLineNum = 278;BA.debugLine="Btn_Post.Initialize(\"Btn_Post\")";
mostCurrent._btn_post.Initialize(mostCurrent.activityBA,"Btn_Post");
 //BA.debugLineNum = 279;BA.debugLine="scrview(0).Panel.AddView(Btn_Post,30%x,12%y * lbl";
mostCurrent._scrview[(int) (0)].getPanel().AddView((android.view.View)(mostCurrent._btn_post.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (30),mostCurrent.activityBA),(int) (anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (12),mostCurrent.activityBA)*mostCurrent._lbl_txt.length+anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (21),mostCurrent.activityBA)),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (40),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (10),mostCurrent.activityBA));
 //BA.debugLineNum = 280;BA.debugLine="Btn_Post.Text = \"提交\"";
mostCurrent._btn_post.setText((Object)("提交"));
 //BA.debugLineNum = 281;BA.debugLine="scrview(0).Panel.Height = 12%y * lbl_txt.Length +";
mostCurrent._scrview[(int) (0)].getPanel().setHeight((int) (anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (12),mostCurrent.activityBA)*mostCurrent._lbl_txt.length+anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (35),mostCurrent.activityBA)));
 //BA.debugLineNum = 284;BA.debugLine="Lst_Set.Initialize(\"Lst_Set\")";
mostCurrent._lst_set.Initialize(mostCurrent.activityBA,"Lst_Set");
 //BA.debugLineNum = 285;BA.debugLine="Activity.AddView(Lst_Set,0,7%y,100%x,88%y)";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._lst_set.getObject()),(int) (0),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (7),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (88),mostCurrent.activityBA));
 //BA.debugLineNum = 286;BA.debugLine="Lst_Set.Color = Colors.LightGray";
mostCurrent._lst_set.setColor(anywheresoftware.b4a.keywords.Common.Colors.LightGray);
 //BA.debugLineNum = 287;BA.debugLine="Lst_Set.Visible = False";
mostCurrent._lst_set.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 288;BA.debugLine="Lst_Set.AddSingleLine(\"无线网络设置\")";
mostCurrent._lst_set.AddSingleLine("无线网络设置");
 //BA.debugLineNum = 289;BA.debugLine="Lst_Set.AddSingleLine(\"服务器设置\")";
mostCurrent._lst_set.AddSingleLine("服务器设置");
 //BA.debugLineNum = 290;BA.debugLine="Lst_Set.AddSingleLine(\"息屏时间设置\")";
mostCurrent._lst_set.AddSingleLine("息屏时间设置");
 //BA.debugLineNum = 291;BA.debugLine="Lst_Set.AddSingleLine(\"系统参数设置\")";
mostCurrent._lst_set.AddSingleLine("系统参数设置");
 //BA.debugLineNum = 292;BA.debugLine="Lst_Set.Visible = False";
mostCurrent._lst_set.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 295;BA.debugLine="Pnl_WifiSet.Initialize(\"\")";
mostCurrent._pnl_wifiset.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 296;BA.debugLine="Activity.AddView(Pnl_WifiSet,5%x,35%y,90%x,35%y)";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._pnl_wifiset.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (5),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (35),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (90),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (35),mostCurrent.activityBA));
 //BA.debugLineNum = 297;BA.debugLine="Pnl_WifiSet.Visible = False";
mostCurrent._pnl_wifiset.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 298;BA.debugLine="Pnl_WifiSet.Color =Colors.Gray";
mostCurrent._pnl_wifiset.setColor(anywheresoftware.b4a.keywords.Common.Colors.Gray);
 //BA.debugLineNum = 299;BA.debugLine="Spn_WifiList.Initialize(\"Spn_WifiList\")";
mostCurrent._spn_wifilist.Initialize(mostCurrent.activityBA,"Spn_WifiList");
 //BA.debugLineNum = 300;BA.debugLine="Pnl_WifiSet.AddView(Spn_WifiList,10%X,5%Y,70%X,8%";
mostCurrent._pnl_wifiset.AddView((android.view.View)(mostCurrent._spn_wifilist.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (10),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (5),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (70),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (8),mostCurrent.activityBA));
 //BA.debugLineNum = 301;BA.debugLine="Btn_WifiConnect.Initialize(\"Btn_WifiConnect\")";
mostCurrent._btn_wificonnect.Initialize(mostCurrent.activityBA,"Btn_WifiConnect");
 //BA.debugLineNum = 302;BA.debugLine="Pnl_WifiSet.AddView(Btn_WifiConnect,30%X,20%Y,30%";
mostCurrent._pnl_wifiset.AddView((android.view.View)(mostCurrent._btn_wificonnect.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (30),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (20),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (30),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (10),mostCurrent.activityBA));
 //BA.debugLineNum = 303;BA.debugLine="Btn_WifiConnect.Text = \"Connect\"";
mostCurrent._btn_wificonnect.setText((Object)("Connect"));
 //BA.debugLineNum = 305;BA.debugLine="End Sub";
return "";
}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        main._process_globals();
subfunc._process_globals();
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 15;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 18;BA.debugLine="Dim db As MSSQL";
_db = new com.tomlost.MSSQL.MSSQL();
 //BA.debugLineNum = 19;BA.debugLine="Dim lst_result As List";
_lst_result = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 20;BA.debugLine="Dim tim As Timer";
_tim = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 21;BA.debugLine="Dim str_setbuff As String";
_str_setbuff = "";
 //BA.debugLineNum = 22;BA.debugLine="Dim Str_Permit As String";
_str_permit = "";
 //BA.debugLineNum = 23;BA.debugLine="Dim phone As Phone";
_phone = new anywheresoftware.b4a.phone.Phone();
 //BA.debugLineNum = 24;BA.debugLine="Dim Battery As JavaObject";
_battery = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 25;BA.debugLine="End Sub";
return "";
}
public static int  _statusbarheight() throws Exception{
anywheresoftware.b4a.agraham.reflection.Reflection _r = null;
anywheresoftware.b4j.object.JavaObject _jo = null;
int _resourceid = 0;
int _size = 0;
 //BA.debugLineNum = 168;BA.debugLine="Sub StatusBarHeight As Int";
 //BA.debugLineNum = 169;BA.debugLine="Dim r As Reflector";
_r = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 170;BA.debugLine="Dim JO As JavaObject";
_jo = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 171;BA.debugLine="R.Target = R.GetContext";
_r.Target = (Object)(_r.GetContext(processBA));
 //BA.debugLineNum = 172;BA.debugLine="JO = R.RunMethod(\"getResources\")";
_jo.setObject((java.lang.Object)(_r.RunMethod("getResources")));
 //BA.debugLineNum = 173;BA.debugLine="Dim ResourceID As Int = JO.RunMethod(\"getIdentifi";
_resourceid = (int)(BA.ObjectToNumber(_jo.RunMethod("getIdentifier",new Object[]{(Object)("status_bar_height"),(Object)("dimen"),(Object)("android")})));
 //BA.debugLineNum = 174;BA.debugLine="Dim Size As Int";
_size = 0;
 //BA.debugLineNum = 175;BA.debugLine="If ResourceID > 0 Then";
if (_resourceid>0) { 
 //BA.debugLineNum = 176;BA.debugLine="Size = JO.RunMethod(\"getDimensionPixelSize\",";
_size = (int)(BA.ObjectToNumber(_jo.RunMethod("getDimensionPixelSize",new Object[]{(Object)(_resourceid)})));
 };
 //BA.debugLineNum = 178;BA.debugLine="Return Size";
if (true) return _size;
 //BA.debugLineNum = 179;BA.debugLine="End Sub";
return 0;
}
public static String  _tim_tick() throws Exception{
 //BA.debugLineNum = 364;BA.debugLine="Sub tim_Tick";
 //BA.debugLineNum = 365;BA.debugLine="Lbl_Status.Text = NumberFormat(Battery.RunMethod(";
mostCurrent._lbl_status.setText((Object)(anywheresoftware.b4a.keywords.Common.NumberFormat((double)(BA.ObjectToNumber(_battery.RunMethod("getBatteryLevel",(Object[])(anywheresoftware.b4a.keywords.Common.Null)))),(int) (0),(int) (0))+"%"));
 //BA.debugLineNum = 366;BA.debugLine="expandCollapseStatusBar(False)";
_expandcollapsestatusbar(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 367;BA.debugLine="Lbl_Time.Text = DateTime.GetYear(DateTime.now) &";
mostCurrent._lbl_time.setText((Object)(BA.NumberToString(anywheresoftware.b4a.keywords.Common.DateTime.GetYear(anywheresoftware.b4a.keywords.Common.DateTime.getNow()))+"-"+anywheresoftware.b4a.keywords.Common.NumberFormat(anywheresoftware.b4a.keywords.Common.DateTime.GetMonth(anywheresoftware.b4a.keywords.Common.DateTime.getNow()),(int) (2),(int) (0))+"-"+anywheresoftware.b4a.keywords.Common.NumberFormat(anywheresoftware.b4a.keywords.Common.DateTime.GetDayOfMonth(anywheresoftware.b4a.keywords.Common.DateTime.getNow()),(int) (2),(int) (0))+" "+anywheresoftware.b4a.keywords.Common.NumberFormat(anywheresoftware.b4a.keywords.Common.DateTime.GetHour(anywheresoftware.b4a.keywords.Common.DateTime.getNow()),(int) (2),(int) (0))+":"+anywheresoftware.b4a.keywords.Common.NumberFormat(anywheresoftware.b4a.keywords.Common.DateTime.GetMinute(anywheresoftware.b4a.keywords.Common.DateTime.getNow()),(int) (2),(int) (0))+":"+anywheresoftware.b4a.keywords.Common.NumberFormat(anywheresoftware.b4a.keywords.Common.DateTime.GetSecond(anywheresoftware.b4a.keywords.Common.DateTime.getNow()),(int) (2),(int) (0))));
 //BA.debugLineNum = 373;BA.debugLine="End Sub";
return "";
}
public static String  _titletxt(String _t) throws Exception{
 //BA.debugLineNum = 321;BA.debugLine="Sub TitleTxt(t As String)";
 //BA.debugLineNum = 322;BA.debugLine="Lbl_Title.Text = t";
mostCurrent._lbl_title.setText((Object)(_t));
 //BA.debugLineNum = 323;BA.debugLine="Select Lbl_Title.Text";
switch (BA.switchObjectToInt(mostCurrent._lbl_title.getText(),"用户登录","功能列表","IGBT模块随工单","设置")) {
case 0:
 //BA.debugLineNum = 325;BA.debugLine="Lbl_Action.Text = \"\"";
mostCurrent._lbl_action.setText((Object)(""));
 //BA.debugLineNum = 326;BA.debugLine="Lbl_Set.Text = \"设置\"";
mostCurrent._lbl_set.setText((Object)("设置"));
 //BA.debugLineNum = 327;BA.debugLine="Pnl_Login.Visible = True";
mostCurrent._pnl_login.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 328;BA.debugLine="scrview(0).Visible = False";
mostCurrent._scrview[(int) (0)].setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 329;BA.debugLine="Lst_Func.Visible = False";
mostCurrent._lst_func.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 330;BA.debugLine="Lst_Set.Visible = False";
mostCurrent._lst_set.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 331;BA.debugLine="Pnl_WifiSet.Visible = False";
mostCurrent._pnl_wifiset.setVisible(anywheresoftware.b4a.keywords.Common.False);
 break;
case 1:
 //BA.debugLineNum = 334;BA.debugLine="Lbl_Action.Text = \"<<退出\"";
mostCurrent._lbl_action.setText((Object)("<<退出"));
 //BA.debugLineNum = 335;BA.debugLine="Lbl_Set.Text = \"设置\"";
mostCurrent._lbl_set.setText((Object)("设置"));
 //BA.debugLineNum = 336;BA.debugLine="Pnl_Login.Visible = False";
mostCurrent._pnl_login.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 337;BA.debugLine="scrview(0).Visible = False";
mostCurrent._scrview[(int) (0)].setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 338;BA.debugLine="Lst_Set.Visible = False";
mostCurrent._lst_set.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 339;BA.debugLine="Lst_Func.Visible = True";
mostCurrent._lst_func.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 340;BA.debugLine="Pnl_WifiSet.Visible = False";
mostCurrent._pnl_wifiset.setVisible(anywheresoftware.b4a.keywords.Common.False);
 break;
case 2:
 //BA.debugLineNum = 343;BA.debugLine="Lbl_Action.Text = \"<<返回\"";
mostCurrent._lbl_action.setText((Object)("<<返回"));
 //BA.debugLineNum = 344;BA.debugLine="Lbl_Set.Text = \"设置\"";
mostCurrent._lbl_set.setText((Object)("设置"));
 //BA.debugLineNum = 345;BA.debugLine="Pnl_Login.Visible = False";
mostCurrent._pnl_login.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 346;BA.debugLine="Lst_Func.Visible = False";
mostCurrent._lst_func.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 347;BA.debugLine="Lst_Set.Visible = False";
mostCurrent._lst_set.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 348;BA.debugLine="scrview(0).Visible = True";
mostCurrent._scrview[(int) (0)].setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 349;BA.debugLine="Pnl_WifiSet.Visible = False";
mostCurrent._pnl_wifiset.setVisible(anywheresoftware.b4a.keywords.Common.False);
 break;
case 3:
 //BA.debugLineNum = 352;BA.debugLine="Lbl_Action.Text = \"<<返回\"";
mostCurrent._lbl_action.setText((Object)("<<返回"));
 //BA.debugLineNum = 353;BA.debugLine="Lbl_Set.Text = \"\"";
mostCurrent._lbl_set.setText((Object)(""));
 //BA.debugLineNum = 354;BA.debugLine="Pnl_Login.Visible = False";
mostCurrent._pnl_login.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 355;BA.debugLine="scrview(0).Visible = False";
mostCurrent._scrview[(int) (0)].setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 356;BA.debugLine="Lst_Set.Visible = True";
mostCurrent._lst_set.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 357;BA.debugLine="Lst_Func.Visible = False";
mostCurrent._lst_func.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 358;BA.debugLine="Pnl_WifiSet.Visible = False";
mostCurrent._pnl_wifiset.setVisible(anywheresoftware.b4a.keywords.Common.False);
 break;
}
;
 //BA.debugLineNum = 361;BA.debugLine="End Sub";
return "";
}
public static String  _wifiscan_scandone(String[] _results,int _count) throws Exception{
 //BA.debugLineNum = 466;BA.debugLine="Sub wifiscan_ScanDone(Results() As String, Count A";
 //BA.debugLineNum = 467;BA.debugLine="Spn_WifiList.Clear";
mostCurrent._spn_wifilist.Clear();
 //BA.debugLineNum = 468;BA.debugLine="Spn_WifiList.Add(\"请选择wifi...\")";
mostCurrent._spn_wifilist.Add("请选择wifi...");
 //BA.debugLineNum = 469;BA.debugLine="Spn_WifiList.AddAll(Results)";
mostCurrent._spn_wifilist.AddAll(anywheresoftware.b4a.keywords.Common.ArrayToList(_results));
 //BA.debugLineNum = 470;BA.debugLine="Spn_WifiList.SelectedIndex = wifisearch(wifi,Spn_";
mostCurrent._spn_wifilist.setSelectedIndex(_wifisearch(mostCurrent._wifi,mostCurrent._spn_wifilist));
 //BA.debugLineNum = 471;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 472;BA.debugLine="End Sub";
return "";
}
public static int  _wifisearch(wifi.MLwifi _w,anywheresoftware.b4a.objects.SpinnerWrapper _l) throws Exception{
int _i = 0;
 //BA.debugLineNum = 474;BA.debugLine="Sub wifisearch(w As MLwifi,l As Spinner) As Int";
 //BA.debugLineNum = 475;BA.debugLine="If w.SSID <> \"\" Then";
if ((_w.SSID()).equals("") == false) { 
 //BA.debugLineNum = 476;BA.debugLine="For i = 0  To l.Size - 1";
{
final int step361 = 1;
final int limit361 = (int) (_l.getSize()-1);
for (_i = (int) (0); (step361 > 0 && _i <= limit361) || (step361 < 0 && _i >= limit361); _i = ((int)(0 + _i + step361))) {
 //BA.debugLineNum = 477;BA.debugLine="If l.GetItem(i).IndexOf(\",\") > 0 Then";
if (_l.GetItem(_i).indexOf(",")>0) { 
 //BA.debugLineNum = 478;BA.debugLine="If l.GetItem(i).SubString2(0,l.GetItem(i).Inde";
if ((_l.GetItem(_i).substring((int) (0),_l.GetItem(_i).indexOf(",")).trim()).equals(_w.SSID().trim())) { 
if (true) return _i;};
 };
 }
};
 };
 //BA.debugLineNum = 482;BA.debugLine="Return 0";
if (true) return (int) (0);
 //BA.debugLineNum = 483;BA.debugLine="End Sub";
return 0;
}


public float getBatteryLevel() {
    Intent batteryIntent = registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    int level = batteryIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
    int scale = batteryIntent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);  

    return ((float)level / (float)scale) * 100.0f;
}  

}
