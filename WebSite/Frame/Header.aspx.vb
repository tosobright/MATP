
Partial Class main
    Inherits System.Web.UI.Page

    Protected Sub Page_Load(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.Load
        Try
            If Convert.ToBoolean(Session("IsLogin")) <> True Then
                Response.Redirect("../MainPage.aspx")
                Response.End()
            End If
        Catch
        End Try
        Lbl_UserInfo.Text = "用户名：" + Convert.ToString(Session("Username"))
        Lbl_Permit.Text = "管理权限：" + Convert.ToString(Session("Permit"))
    End Sub

    Protected Sub Timer1_Tick(ByVal sender As Object, ByVal e As System.EventArgs) Handles Timer1.Tick
        Lbl_Time.Text = Date.Now.ToLongDateString + " " + DateTime.Now.ToLongTimeString
    End Sub

    Protected Sub Btn_Exit_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles Btn_Exit.Click
        Session("IsLogin") = False
        Response.Write(" <script> parent.window.location.href= '../default.aspx ' </script> ")

    End Sub
End Class
