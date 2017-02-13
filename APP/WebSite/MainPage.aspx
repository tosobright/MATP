<%@ page language="VB" autoeventwireup="false" inherits="MainPage, App_Web_ekkhknrj" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>
    数据管理系统
    </title>
</head>
<frameset rows="85,*" frameborder="no" border="0" framespacing="0"> 
  <frame src="frame/Header.aspx" name="topframe" scrolling="No" id="topframe" title="topframe" /> 
  <frameset id="mainframeset" cols="200,*" frameborder="no" border="0" framespacing="0"> 
        <frame src="frame/Menu.aspx" name="leftframe" noresize="noresize" id="leftframe" title="leftframe" style="background-color: #99CCFF" /> 
        <frame src="frame/welcome.aspx" name="mainframe" id="mainframe" title="mainframe" /> 
  </frameset> 
</frameset> 
<noframes> 
    <body> 
    </body> 
</noframes> 
</html>
