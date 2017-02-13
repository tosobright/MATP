<%@ page language="VB" autoeventwireup="false" inherits="main, App_Web_uhtucisq" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1" runat="server">
    <title>数据管理系统</title>    
    <link href="../App_Themes/admin.css" rel="stylesheet" type="text/css"></link>
</head>
<body>
    <form id="Mainform" runat="server" >
     <asp:ScriptManager ID="ScriptManager1" runat="server">
    </asp:ScriptManager>
    <asp:Timer ID="Timer1" runat="server" Interval="500">
    </asp:Timer>
<div id="header">
  <div class="top" style="overflow: hidden; top: 0px; left: 0px;">
    <div class="logo">
        <asp:Label ID="Label1" runat="server" Font-Size="X-Large" 
            Text="数据管理系统"></asp:Label>
      </div>
    <div class="user"> 
    <asp:UpdatePanel ID="UpdatePanelTime" runat="server">
                    <ContentTemplate>
                        <asp:Label ID="Lbl_Time" runat="server"></asp:Label>
                        &nbsp;&nbsp;&nbsp;
                    </ContentTemplate>
                    <Triggers>
                        <asp:AsyncPostBackTrigger ControlID="Timer1" EventName="Tick" />
                    </Triggers>
                </asp:UpdatePanel>
                <div class="username">
                    <asp:Label ID="Lbl_UserInfo" runat="server"></asp:Label>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <asp:Label ID="Lbl_Permit" runat="server"></asp:Label>
&nbsp;&nbsp;&nbsp; </div>
      <div class="userbtn">
          <asp:Button ID="Btn_Chgpwd" runat="server" BackColor="White" 
              BorderColor="#003300" BorderStyle="Solid" BorderWidth="1px" Height="25px" 
              Text="修改密码" Width="100px" Visible="False" />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <asp:Button ID="Btn_Exit" runat="server" BackColor="White" 
              BorderColor="#003300" BorderStyle="Solid" BorderWidth="1px" Height="25px" 
              Text="登出" Width="100px" />
          &nbsp;&nbsp;</div>
    </div>
  </div>
</div>
</form>
</body>
</html>
