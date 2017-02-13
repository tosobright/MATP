<%@ page language="VB" autoeventwireup="false" inherits="data, App_Web_data.aspx.6d195dc8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
    <div>    
        <asp:GridView ID="GridView2" runat="server" AutoGenerateColumns="False" 
            DataKeyNames="ID" DataSourceID="SqlDataSource2" 
            EmptyDataText="没有可显示的数据记录。" CellPadding="4" ForeColor="#333333" 
            GridLines="None">
            <AlternatingRowStyle BackColor="White" />
            <Columns>
                <asp:BoundField DataField="ID" HeaderText="ID" ReadOnly="True" 
                    SortExpression="ID" />
                <asp:BoundField DataField="reback" HeaderText="reback" 
                    SortExpression="reback" />
                <asp:BoundField DataField="title" HeaderText="title" SortExpression="title" />
                <asp:BoundField DataField="body" HeaderText="body" SortExpression="body" />
                <asp:BoundField DataField="name" HeaderText="name" SortExpression="name" />
                <asp:BoundField DataField="email" HeaderText="email" SortExpression="email" />
                <asp:BoundField DataField="submitdate" HeaderText="submitdate" 
                    SortExpression="submitdate" />
                <asp:CommandField ShowDeleteButton="True" ShowEditButton="True" />
                <asp:TemplateField>
                    <ItemTemplate>
                        <asp:Button ID="Button1" runat="server" CausesValidation="False" CommandName="Delete"
                            onclientclick="return confirm('Are you sure to del')" Text="del" />
                    </ItemTemplate>
                </asp:TemplateField>
            </Columns>
            <EditRowStyle BackColor="#2461BF" />
            <FooterStyle BackColor="#507CD1" Font-Bold="True" ForeColor="White" />
            <HeaderStyle BackColor="#507CD1" Font-Bold="True" ForeColor="White" />
            <PagerStyle BackColor="#2461BF" ForeColor="White" HorizontalAlign="Center" />
            <RowStyle BackColor="#EFF3FB" />
            <SelectedRowStyle BackColor="#D1DDF1" Font-Bold="True" ForeColor="#333333" />
            <SortedAscendingCellStyle BackColor="#F5F7FB" />
            <SortedAscendingHeaderStyle BackColor="#6D95E1" />
            <SortedDescendingCellStyle BackColor="#E9EBEF" />
            <SortedDescendingHeaderStyle BackColor="#4870BE" />
        </asp:GridView>
        <asp:SqlDataSource ID="SqlDataSource2" runat="server" 
            ConnectionString="Driver={Driver do Microsoft Access (*.mdb)};dbq=|DataDirectory|\db.mdb;defaultdir=|DataDirectory|;driverid=25;fil=MS Access;maxbuffersize=2048;maxscanrows=8;pagetimeout=5;safetransactions=0;threads=3;uid=admin;usercommitsync=Yes;pwd=taoyiyang" 
            ProviderName="System.Data.Odbc" 
            DeleteCommand="DELETE FROM [guest] WHERE [ID] = ?" 
            InsertCommand="INSERT INTO [guest] ([ID], [reback], [title], [body], [name], [email], [submitdate]) VALUES (?, ?, ?, ?, ?, ?, ?)" 
            SelectCommand="SELECT [ID], [reback], [title], [body], [name], [email], [submitdate] FROM [guest]" 
            UpdateCommand="UPDATE [guest] SET [reback] = ?, [title] = ?, [body] = ?, [name] = ?, [email] = ?, [submitdate] = ? WHERE [ID] = ?">
            <DeleteParameters>
                <asp:Parameter Name="ID" Type="Int32" />
            </DeleteParameters>
            <InsertParameters>
                <asp:Parameter Name="ID" Type="Int32" />
                <asp:Parameter Name="reback" Type="String" />
                <asp:Parameter Name="title" Type="String" />
                <asp:Parameter Name="body" Type="String" />
                <asp:Parameter Name="name" Type="String" />
                <asp:Parameter Name="email" Type="String" />
                <asp:Parameter Name="submitdate" Type="DateTime" />
            </InsertParameters>
            <UpdateParameters>
                <asp:Parameter Name="reback" Type="String" />
                <asp:Parameter Name="title" Type="String" />
                <asp:Parameter Name="body" Type="String" />
                <asp:Parameter Name="name" Type="String" />
                <asp:Parameter Name="email" Type="String" />
                <asp:Parameter Name="submitdate" Type="DateTime" />
                <asp:Parameter Name="ID" Type="Int32" />
            </UpdateParameters>
        </asp:SqlDataSource>
    </div>
    </form>
</body>
</html>
