using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace Tailorin.Views
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class LoginPage : ContentPage
    {
        public LoginPage()
        {
            InitializeComponent();
            Init();
        }

        public void Init()
        {
            BackgroundImageSource = "background_halfp_halfpd.xml";
        }

        public async void loginProcedure(object sender, EventArgs e)
        {
            try
            {
                string mail = UsernameEntry.Text;
                string password = PasswordEntry.Text;
                var Login = DependencyService.Get<Interfaces.IFirebaseTailorin>();
                string token = await Login.LoginMailPassword(mail, password);
                await DisplayAlert("Tooken", token, "ok");
                
                await Navigation.PushModalAsync(new NavigationPage(new HomePage()));
            } catch (System.Exception err)
            {
                await DisplayAlert("Errore",err.Message, "OK");
            }
                        

        }

        public void toRegisterPage(object sender, EventArgs e)
        {
            Navigation.PushModalAsync(new RegisterPage());
        }
        
    }
}