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

        public void loginProcedure(object sender, EventArgs e)
        {

        }

        public void toRegisterPage(object sender, EventArgs e)
        {
            Navigation.PushModalAsync(new RegisterPage());
        }
        
    }
}