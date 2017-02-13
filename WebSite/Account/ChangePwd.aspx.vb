Imports System.Data.SqlClient   'SQLServer

Partial Class Account_ChangePwd
    Inherits System.Web.UI.Page

    Protected Sub Btn_Change_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles Btn_Change.Click
        If Txt_NewPwd.Text <> Txt_ConfirmPwd.Text Then
            Response.Write("<script language=javascript>alert('两次密码不一致');history.back();</script>")
        Else
            Dim Str_DBconn As String = ConfigurationManager.ConnectionStrings.Item(1).ToString
            Dim sqlconn As SqlConnection = New SqlConnection
            Dim SelectStr As String = "SELECT password FROM [user] where id ='" & Session("Username") & "'"
            Dim UpdateStr As String = "Update [user] SET password='" & Txt_NewPwd.Text & "'" & "where id ='" & Session("Username") & "'"
            Dim SelectCmd As SqlCommand = New SqlCommand(SelectStr)
            Dim UpdateCmd As SqlCommand = New SqlCommand(UpdateStr)
            Dim dr As SqlDataReader

            sqlconn.ConnectionString = Str_DBconn
            sqlconn.Open()
            SelectCmd.Connection = sqlconn
            dr = SelectCmd.ExecuteReader()

            If (dr.Read()) Then
                Dim cmdresult As String
                cmdresult = dr(0).ToString()
                dr.Close()

                If cmdresult = Txt_Pwd.Text Then
                    '执行更改密码sql语句
                    UpdateCmd.Connection = sqlconn
                    Dim updaterow As Integer = UpdateCmd.ExecuteNonQuery
                    If updaterow = 1 Then
                        Response.Write("<script language=javascript>alert('密码修改成功!');history.back();</script>")
                    End If
                Else
                    Response.Write("<script language=javascript>alert('原密码输入错误!');history.back();</script>")
                End If
            Else
                Response.Write("<script language=javascript>alert('database error!');history.back();</script>")
            End If

            sqlconn.Close()

        End If
    End Sub

    Protected Sub Page_Load(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.Load
        Try
            If Convert.ToBoolean(Session("IsLogin")) <> True Then
                Response.Redirect("../MainPage.aspx")
                Response.End()
            End If
        Catch
        End Try
    End Sub
End Class
