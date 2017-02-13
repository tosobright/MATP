Imports System.Data
Imports System.Data.OleDb       'Access
Imports System.Data.SqlClient   'SQLServer

Partial Class Login
    Inherits System.Web.UI.Page

    Protected Sub Btn_Login_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles Btn_Login.Click
        Dim Str_DBconn As String = ConfigurationManager.ConnectionStrings.Item(1).ToString
        Dim sqlconn As SqlConnection = New SqlConnection
        Dim cmdstr As String = "SELECT password, authority FROM [user] where id ='" & Txt_User.Text & "'"
        Dim cmd As SqlCommand = New SqlCommand(cmdstr)
        Dim dr As SqlDataReader

        sqlconn.ConnectionString = Str_DBconn
        sqlconn.Open()
        cmd.Connection = sqlconn
        dr = cmd.ExecuteReader()

        If (dr.Read()) Then
            Dim cmdresult As String
            cmdresult = dr(0).ToString()

            If cmdresult = Txt_Pwd.Text Then
                Session("IsLogin") = True
                Session("Username") = Txt_User.Text
                Session("Permit") = dr(1).ToString
                Response.Redirect("MainPage.aspx")
                Response.End()
            Else
                Response.Write("<script language=javascript>alert('Please input user&password!');history.back();</script>")
            End If
        Else
            Response.Write("<script language=javascript>alert('user or password error!');history.back();</script>")
        End If
        dr.Close()

        sqlconn.Close()
    End Sub

End Class


