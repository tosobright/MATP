<%@ Page Language="VB" AutoEventWireup="false" CodeFile="menu.aspx.vb" Inherits="menu" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
    <div style="width: 150px; height: 300px">
    
        <asp:TreeView ID="TreeView1" runat="server" ImageSet="Arrows" ShowLines="True">
            <HoverNodeStyle Font-Underline="True" ForeColor="#5555DD" />
            <Nodes>
                <asp:TreeNode Text="欢迎页面" Value="新建节点" NavigateUrl="~/Frame/welcome.aspx" 
                    Target="mainframe"></asp:TreeNode>
                <asp:TreeNode Text="测试数据" Value="新建节点" Target="mainframe" Expanded="False">
                    <asp:TreeNode NavigateUrl="~/OrderMgnt/OrderSelect.aspx" Target="mainframe" 
                        Text="数据查询" Value="新建节点"></asp:TreeNode>
                </asp:TreeNode>
                <asp:TreeNode Text="账户管理" Value="新建节点" Target="mainframe" Expanded="False">
                    <asp:TreeNode NavigateUrl="~/Account/UserSelect.aspx" Target="mainframe" 
                        Text="账户查看" Value="账户查看"></asp:TreeNode>
                    <asp:TreeNode Text="修改密码" Value="修改密码" 
                        NavigateUrl="~/Account/ChangePwd.aspx" Target="mainframe">
                    </asp:TreeNode>
                    <asp:TreeNode NavigateUrl="~/Account/ChangePermit.aspx" Target="mainframe" 
                        Text="权限管理" Value="权限管理"></asp:TreeNode>
                </asp:TreeNode>
            </Nodes>
            <NodeStyle Font-Names="Tahoma" Font-Size="10pt" ForeColor="Black" 
                HorizontalPadding="5px" NodeSpacing="0px" VerticalPadding="0px" />
            <ParentNodeStyle Font-Bold="False" />
            <SelectedNodeStyle Font-Underline="True" ForeColor="#5555DD" 
                HorizontalPadding="0px" VerticalPadding="0px" />
        </asp:TreeView>
    
    </div>
    </form>
</body>
</html>
