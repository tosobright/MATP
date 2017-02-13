
Partial Class menu
    Inherits System.Web.UI.Page

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
