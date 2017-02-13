<%@ page language="VB" autoeventwireup="false" inherits="Login, App_Web_login.aspx.cdcab7d2" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>系统登录</title>
    <style type="text/css">
        .style1
        {
            width: 100%;
        }
        .style2
        {
            width: 200px;
        }
    </style>
</head>
<body>
    <form id="Loginfrom" defaultbutton="Btn_Login" defaultfocus="Txt_User" runat="server">
    <div>    
        <div id="Header" align="center" 
            style="width: 100%; height: 200px; background-color: #3a6ea5; background-image: none; top: 0px;">
            <img alt="" src="" 
                style="height: 71px; width: 127px; margin-right: auto; margin-left: auto; margin-top: 100px;" /></div>
    
    </div>
    <table align="center" class="style1">
            <tr>
                <td>
                    &nbsp;</td>
                <td class="style2">
                    &nbsp;</td>
                <td>
                    &nbsp;</td>
            </tr>
            <tr>
                <td>
                    &nbsp;</td>
                <td class="style2">
        <asp:Label ID="Lbl_User" runat="server" Font-Names="新宋体" Font-Overline="False" 
            Font-Size="12pt" Height="20px" Text="用户名：" ></asp:Label>
                </td>
                <td>
                    &nbsp;</td>
            </tr>
            <tr>
                <td>
                    &nbsp;</td>
                <td class="style2">
        <asp:TextBox ID="Txt_User" runat="server" BorderColor="#3A6EA5" 
            BorderWidth="1px" Height="25px" TabIndex="1" ToolTip="请输入用户名" 
            Width="200px" AutoCompleteType="DisplayName" Font-Size="Larger"></asp:TextBox>
                </td>
                <td>
                    &nbsp;</td>
            </tr>
            <tr>
                <td>
                    &nbsp;</td>
                <td class="style2">
                    &nbsp;</td>
                <td>
                    &nbsp;</td>
            </tr>
            <tr>
                <td>
                    &nbsp;</td>
                <td class="style2">
        <asp:Label ID="Lbl_Pwd" runat="server" BorderStyle="None" Height="20px" 
            Text="密码：" Width="198px"></asp:Label>
                </td>
                <td>
                    &nbsp;</td>
            </tr>
            <tr>
                <td>
                    &nbsp;</td>
                <td class="style2">
        <asp:TextBox ID="Txt_Pwd" runat="server" BorderColor="#3A6EA5" 
            BorderWidth="1px" Height="25px" TabIndex="2" TextMode="Password" 
            ToolTip="请输入密码" Width="200px" AutoCompleteType="Disabled" 
            Font-Size="Larger"></asp:TextBox>
                </td>
                <td>
                    &nbsp;</td>
            </tr>
            <tr>
                <td>
                    &nbsp;</td>
                <td class="style2">
                    &nbsp;</td>
                <td>
                    &nbsp;</td>
            </tr>
            <tr>
                <td>
                    &nbsp;</td>
                <td class="style2">
        <asp:Button ID="Btn_Login" runat="server" BackColor="#3A6EA5" 
            BorderColor="#3A6EA5" BorderStyle="None" ForeColor="White" Height="30px" 
            TabIndex="3" Text="登录" Width="80px" />
                </td>
                <td>
                    &nbsp;</td>
            </tr>
            <tr>
                <td>
                    &nbsp;</td>
                <td class="style2">
                    &nbsp;</td>
                <td>
                    &nbsp;</td>
            </tr>
        </table>
    </form>
</body>
</html>
