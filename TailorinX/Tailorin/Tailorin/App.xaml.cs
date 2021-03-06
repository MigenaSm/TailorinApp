﻿using System;
using Tailorin.Views;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace Tailorin
{
    public partial class App : Application
    {
        public App()
        {
            InitializeComponent();

            if (DependencyService.Get<Interfaces.IFirebaseTailorin>().isLogged())
            {
                MainPage = new NavigationPage(new HomePage());
            } else
            {
                MainPage = new LoginPage();
            }
        }

        protected override void OnStart()
        {
        }

        protected override void OnSleep()
        {
        }

        protected override void OnResume()
        {
        }
    }
}
