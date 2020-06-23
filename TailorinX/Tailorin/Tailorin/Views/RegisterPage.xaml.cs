using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace Tailorin.Views
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class RegisterPage : ContentPage
    {

        public RegisterPage()
        {
            InitializeComponent();
        }

        public async void Register(object sender, EventArgs e)
        {
            try
            {
                string Snome = nome.Text;
                string Scognome = cognome.Text;
                string Spassword = password.Text;
                string Smail = email.Text;
                string Spassword_conf = password_conf.Text;

                if(Spassword.CompareTo(Spassword_conf) == 0)
                {
                    var register = DependencyService.Get<Interfaces.IFirebaseTailorin>();
                    await register.Register(Smail, Spassword, Snome, Scognome);
                    await Navigation.PushModalAsync(new NavigationPage(new HomePage()));
                }

                else
                {
                    passwordError.Text = "Le password non coincidono";
                    passwordError.IsVisible = true;
                    password_conf.Margin = new Thickness(20, 20, 20, 0);
                    password_conf.Text = "";
                }
                
            }
            catch (FormatException exception)
            {
                mailError.Text = "Email non valida";
                mailError.IsVisible = true;
                password.Margin = new Thickness(20, 20, 20, 0);
            }
            catch (System.Exception err)
            {
                if(nome.Text == null)
                {
                    nomeError.Text = "Il campo nome è vuoto";
                    nomeError.IsVisible = true;
                    cognome.Margin = new Thickness(20, 20, 20, 0);
                }
                if (cognome.Text == null)
                {
                    cognomeError.Text = "Il campo cognome è vuoto";
                    cognomeError.IsVisible = true;
                    email.Margin = new Thickness(20, 20, 20, 0);
                }
                if (email.Text == null)
                {
                    mailError.Text = "Il campo email è vuoto";
                    mailError.IsVisible = true;
                    password.Margin = new Thickness(20, 20, 20, 0);
                }
                if (password.Text == null)
                {
                    passwordError.Text = "Password necessaria";
                    passwordError.IsVisible = true;
                    password_conf.Margin = new Thickness(20, 20, 20, 0);
                }
                if (password_conf.Text == null)
                {
                    passwordConfError.Text = "Conferma password necessaria";
                    passwordConfError.IsVisible = true;
                }
            }
        }

        public void removeError(object sender, TextChangedEventArgs e)
        {
            Entry entry = (Entry)sender;
            if(entry.Id == nome.Id && nomeError.IsVisible)
            {
                nomeError.IsVisible = false;
                cognome.Margin = new Thickness(20, 45, 20, 0);
            }
            if (entry.Id == cognome.Id && cognomeError.IsVisible)
            {
                cognomeError.IsVisible = false;
                email.Margin = new Thickness(20, 45, 20, 0);
            }
            if (entry.Id == email.Id && mailError.IsVisible)
            {
                mailError.IsVisible = false;
                password.Margin = new Thickness(20, 45, 20, 0);
            }
            if (entry.Id == password.Id && passwordError.IsVisible)
            {
                passwordError.IsVisible = false;
                password_conf.Margin = new Thickness(20, 45, 20, 0);
            }
            if (entry.Id == password_conf.Id && passwordConfError.IsVisible)
            {
                passwordConfError.IsVisible = false;
            }
        }
    }
}