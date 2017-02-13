<%@ page language="VB" autoeventwireup="false" inherits="menu, App_Web_menu.aspx.6d195dc8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
    <div style="width: 150px; height: 700px">
    
        <asp:TreeView ID="TreeView1" runat="server" ImageSet="Arrows" ShowLines="True">
            <HoverNodeStyle Font-Underline="True" ForeColor="#5555DD" />
            <Nodes>
                <asp:TreeNode Text="欢迎页面" Value="新建节点" NavigateUrl="~/Frame/welcome.aspx" 
                    Target="mainframe"></asp:TreeNode>
                <asp:TreeNode Text="登录" Value="新建节点" NavigateUrl="~/Login.aspx" 
                    Target="mainframe"></asp:TreeNode>
                <asp:TreeNode Text="数据" Value="新建节点" NavigateUrl="~/Frame/data.aspx" 
                    Target="mainframe"></asp:TreeNode>
                <asp:TreeNode Text="订单管理" Value="新建节点" Target="mainframe" Expanded="False">
                    <asp:TreeNode NavigateUrl="~/OrderMgnt/OrderAdd.aspx" Target="mainframe" 
                        Text="订单新增" Value="新建节点"></asp:TreeNode>
                    <asp:TreeNode NavigateUrl="~/OrderMgnt/OrderSelect.aspx" Target="mainframe" 
                        Text="订单查询" Value="新建节点"></asp:TreeNode>
                </asp:TreeNode>
                <asp:TreeNode Text="产品管理" Value="新建节点" Target="mainframe" Expanded="False">
                    <asp:TreeNode Text="查询产品" Value="新建节点" 
                        NavigateUrl="~/ProductMgnt/ProductSelect.aspx" Target="mainframe">
                    </asp:TreeNode>
                    <asp:TreeNode NavigateUrl="~/ProductMgnt/ProductAdd.aspx" Target="mainframe" 
                        Text="新增产品类别" Value="新建节点"></asp:TreeNode>
                </asp:TreeNode>
                <asp:TreeNode Text="新建节点" Value="新建节点"></asp:TreeNode>
                <asp:TreeNode Text="新建节点" Value="新建节点"></asp:TreeNode>
                <asp:TreeNode Text="新建节点" Value="新建节点"></asp:TreeNode>
                <asp:TreeNode Text="新建节点" Value="新建节点"></asp:TreeNode>
                <asp:TreeNode Text="新建节点" Value="新建节点"></asp:TreeNode>
                <asp:TreeNode Text="新建节点" Value="新建节点"></asp:TreeNode>
                <asp:TreeNode Text="新建节点" Value="新建节点"></asp:TreeNode>
                <asp:TreeNode Text="新建节点" Value="新建节点"></asp:TreeNode>
                <asp:TreeNode Text="新建节点" Value="新建节点" Expanded="False">
                    <asp:TreeNode Text="新建节点" Value="新建节点"></asp:TreeNode>
                </asp:TreeNode>
                <asp:TreeNode Text="新建节点" Value="新建节点"></asp:TreeNode>
                <asp:TreeNode Text="新建节点" Value="新建节点"></asp:TreeNode>
                <asp:TreeNode Text="新建节点" Value="新建节点" Expanded="False">
                    <asp:TreeNode Text="新建节点" Value="新建节点"></asp:TreeNode>
                </asp:TreeNode>
                <asp:TreeNode Text="新建节点" Value="新建节点"></asp:TreeNode>
                <asp:TreeNode Text="新建节点" Value="新建节点"></asp:TreeNode>
                <asp:TreeNode Text="新建节点" Value="新建节点"></asp:TreeNode>
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
