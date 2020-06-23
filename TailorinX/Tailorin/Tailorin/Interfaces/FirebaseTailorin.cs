using System;
using System.Collections.Generic;
using System.Runtime.CompilerServices;
using System.Text;
using System.Threading.Tasks;


namespace Tailorin.Interfaces
{
    public interface IFirebaseTailorin
    {

        Task<string> LoginMailPassword(string mail, string password);

        Task Register(string mail, string password, string nome, string cognome);

        bool isLogged();

    }
}
