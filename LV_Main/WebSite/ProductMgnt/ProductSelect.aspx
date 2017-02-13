<%@ page language="VB" autoeventwireup="false" inherits="ProductMgnt_ProductSelect, App_Web_productselect.aspx.a181b559" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
    <style type="text/css">
        .style1
        {
            width: 100%;
        }
        .style2
        {
        }
        .style3
        {
            width: 53px;
        }
        .style4
        {
            width: 17px;
        }
        .style5
        {
            width: 14px;
        }
        .style6
        {
            width: 46px;
        }
        .style7
        {
            width: 15px;
        }
        .style8
        {
            width: 56px;
        }
    </style>
</head>
<body>
    <form id="form1" runat="server">
    <div>
    
        <table class="style1" style="width: 800px">
            <tr>
                <td class="style4">
                    &nbsp;</td>
                <td class="style6">
                    &nbsp;</td>
                <td>
                    &nbsp;</td>
                <td class="style4">
                    &nbsp;</td>
                <td class="style2">
                    &nbsp;</td>
                <td>
                    &nbsp;</td>
                <td class="style4">
                    &nbsp;</td>
                <td class="style8">
                    &nbsp;</td>
                <td>
                    &nbsp;</td>
            </tr>
            <tr>
                <td class="style7">
                    &nbsp;</td>
                <td class="style6">
                    </td>
                <td>
                    </td>
                <td class="style4">
                    &nbsp;</td>
                <td class="style3">
                    </td>
                <td>
                    </td>
                <td class="style5">
                    &nbsp;</td>
                <td class="style8">
                    </td>
                <td>
                    </td>
            </tr>
            <tr>
                <td class="style7">
                    &nbsp;</td>
                <td class="style6">
                    &nbsp;</td>
                <td>
                    &nbsp;</td>
                <td class="style4">
                    &nbsp;</td>
                <td class="style3">
                    &nbsp;</td>
                <td>
                    &nbsp;</td>
                <td class="style5">
                    &nbsp;</td>
                <td class="style8">
                    &nbsp;</td>
                <td>
                    &nbsp;</td>
            </tr>
            <tr>
                <td class="style7">
                    &nbsp;</td>
                <td class="style6">
                    &nbsp;</td>
                <td>
                    &nbsp;</td>
                <td class="style4">
                    &nbsp;</td>
                <td class="style3">
                    &nbsp;</td>
                <td>
                    &nbsp;</td>
                <td class="style5">
                    &nbsp;</td>
                <td class="style8">
                    &nbsp;</td>
                <td>
                    &nbsp;</td>
            </tr>
            <tr>
                <td colspan="9" class="style2">
                    <asp:GridView ID="GridView1" runat="server" AutoGenerateColumns="False" 
                        CellPadding="4" DataSourceID="AccessDataSource1" EmptyDataText="没有可显示的数据记录。" 
                        ForeColor="#333333" GridLines="None">
                        <AlternatingRowStyle BackColor="White" />
                        <Columns>
                            <asp:BoundField DataField="ProName" HeaderText="产品名称" 
                                SortExpression="ProName" />
                            <asp:BoundField DataField="SN" HeaderText="产品编码" 
                                SortExpression="SN" />
                            <asp:BoundField DataField="Type" HeaderText="产品种类" SortExpression="Type" />
                            <asp:BoundField DataField="Model" HeaderText="产品模型" SortExpression="Model" />
                            <asp:BoundField DataField="Dimension" HeaderText="产品规格" 
                                SortExpression="Dimension" />
                            <asp:BoundField DataField="Price" HeaderText="产品价格" 
                                SortExpression="Price" DataFormatString="{0:C2}" HtmlEncode="False" />
                            <asp:BoundField DataField="Comment" HeaderText="产品备注" 
                                SortExpression="Comment" />
                            <asp:ButtonField ButtonType="Button" Text="删除" />
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
                    <asp:AccessDataSource ID="AccessDataSource1" runat="server" 
                        DataFile="~/App_Data/db.mdb" 
                        
                        SelectCommand="SELECT `ProName`, `Comment`, `SN`, `Type`, `Model`, `Dimension`, `Price` FROM `Product`">
                    </asp:AccessDataSource>
                </td>
            </tr>
        </table>
    
    </div>
    </form>
</body>
</html>
