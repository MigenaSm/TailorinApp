using Android.Content;
using Android.Content.Res;
using Tailorin.CustomeElements;
using Tailorin.Droid.CustomRenderer;
using Xamarin.Forms;
using Xamarin.Forms.Platform.Android;

[assembly: ExportRenderer(typeof(WhiteEntry), typeof(WhiteEntryRenderer))]
namespace Tailorin.Droid.CustomRenderer
{
    public class WhiteEntryRenderer : EntryRenderer
    {
        public WhiteEntryRenderer(Context context) : base(context) { }

        protected override void OnElementChanged(ElementChangedEventArgs<Entry> e)
        {
            base.OnElementChanged(e);

            if(Control != null)
            {
                Control.BackgroundTintList = ColorStateList.ValueOf(Android.Graphics.Color.White);
            }
        }
    }
}