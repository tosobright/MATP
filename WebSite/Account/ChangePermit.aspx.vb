
Partial Class Account_ChangePermit
    Inherits System.Web.UI.Page

    Protected Sub Page_Load(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.Load
        Try
            If Convert.ToBoolean(Session("IsLogin")) <> True Then
                Response.Redirect("../MainPage.aspx")
                Response.End()
            End If
        Catch
        End Try
        If Session("permit") <> "admin" Then
            Response.Write("<script language=javascript>alert('无权限!');history.back();</script>")
            Response.Redirect("UserSelect.aspx")
        End If
    End Sub
End Class
