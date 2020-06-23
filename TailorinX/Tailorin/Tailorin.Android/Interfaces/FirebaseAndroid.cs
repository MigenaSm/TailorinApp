using Firebase.Annotations;
using Firebase.Auth;
using Java.Lang;
using System;
using System.ComponentModel;
using System.Runtime.CompilerServices;
using System.Threading.Tasks;
using Tailorin.Droid.Interfaces;
using Tailorin.Interfaces;
using Xamarin.Essentials;

[assembly: Xamarin.Forms.Dependency(typeof(FirebaseAndroid))]
namespace Tailorin.Droid.Interfaces
{
    public class FirebaseAndroid : IFirebaseTailorin
    {
        public static int RESULT_OK = 1;
        public static int RESULT_FAULT = 0;

        public bool isLogged()
        {
            if (FirebaseAuth.Instance.CurrentUser != null) return true;
            else return false;
        }

        public async Task<string> LoginMailPassword(string mail, string password)
        {
            try
            {
                var user = await FirebaseAuth.Instance.SignInWithEmailAndPasswordAsync(mail, password);
                var token = await user.User.GetIdTokenAsync(false);                
                return token.Token;

            } catch(FirebaseAuthInvalidUserException exception)
            {
                throw exception;
            }
            catch(System.Exception exception)
            {
                throw exception;
            }
        }

        public async Task Register(string mail, string password, string nome, string cognome)
        {           
            try
            {
                await FirebaseAuth.Instance.CreateUserWithEmailAndPasswordAsync(mail, password).ContinueWith(task =>
                {
                    if (task.IsCompletedSuccessfully)
                    {
                        UserProfileChangeRequest request = new UserProfileChangeRequest.Builder().SetDisplayName(
                            nome + " " + cognome).Build();
                        task.Result.User.UpdateProfileAsync(request);
                    }
                    else
                    {
                        throw new FormatException();
                    }
                });
            }
            catch (FirebaseAuthInvalidUserException exception)
            {
                throw exception;
            }
            catch (System.Exception exception)
            {
                throw exception;
            }
        }
    }
}