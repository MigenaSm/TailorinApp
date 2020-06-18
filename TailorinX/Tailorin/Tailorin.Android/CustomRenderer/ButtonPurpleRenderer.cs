using Android.Content;
using Tailorin.CustomeElements;
using Tailorin.Droid.CustomRenderer;
using Xamarin.Forms;
using Xamarin.Forms.Platform.Android;

[assembly: ExportRenderer(typeof(ButtonPurple), typeof(ButtonPurpleRenderer))]
namespace Tailorin.Droid.CustomRenderer
{
    public class ButtonPurpleRenderer : ButtonRenderer
    {
        public ButtonPurpleRenderer(Context context) : base(context) { }

        protected override void OnElementChanged(ElementChangedEventArgs<Xamarin.Forms.Button> e)
        {
            base.OnElementChanged(e);

            if(Control != null)
            {
                Control.SetBackgroundResource(Resource.Drawable.button_purple_ripple_white);
            }
        }
    }
}