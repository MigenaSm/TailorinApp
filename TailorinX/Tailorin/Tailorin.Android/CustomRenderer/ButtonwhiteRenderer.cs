using Android.Content;
using Tailorin.CustomeElements;
using Tailorin.Droid.CustomRenderer;
using Xamarin.Forms;
using Xamarin.Forms.Platform.Android;

[assembly: ExportRenderer(typeof(ButtonWhite), typeof(ButtonwhiteRenderer))]
namespace Tailorin.Droid.CustomRenderer
{
    public class ButtonwhiteRenderer : ButtonRenderer
    {
        public ButtonwhiteRenderer(Context context) : base(context) { }

        protected override void OnElementChanged(ElementChangedEventArgs<Xamarin.Forms.Button> e)
        {
            base.OnElementChanged(e);

            if(Control != null)
            {
                Control.SetBackgroundResource(Resource.Drawable.button_white_purple_ripple);
            }
        }
    }
}