using System;

using Android.App;
using Android.Content.PM;
using Android.Runtime;
using Android.Views;
using Android.Widget;
using Android.OS;
using Android.Content;
using System.IO;
using Xamarin_Example.Droid;

[assembly: Xamarin.Forms.Dependency(typeof(AndroidPickPhoto))]
namespace Xamarin_Example.Droid
{
    public class AndroidPickPhoto : IPhotoPicker
    {
        public static MainActivity mainActivity = null;
        public void PickPhoto()
        {
            Intent intent = new Intent();
            intent.SetType("image/*");
            intent.SetAction(Intent.ActionGetContent);
            if (mainActivity != null)
            {
                mainActivity.StartActivityForResult(intent, 1);
            }
        }

        public void SetPhotoCallback(PhotoResult cb)
        {
            mainActivity.callback = cb;
        }
    }

    [Activity(Label = "Xamarin_Example", Icon = "@mipmap/icon", Theme = "@style/MainTheme", MainLauncher = true, ConfigurationChanges = ConfigChanges.ScreenSize | ConfigChanges.Orientation | ConfigChanges.UiMode | ConfigChanges.ScreenLayout | ConfigChanges.SmallestScreenSize )]
    public class MainActivity : global::Xamarin.Forms.Platform.Android.FormsAppCompatActivity
    {
        public PhotoResult callback = null;
        protected override void OnActivityResult(int requestCode, Result resultCode, Intent data)
        {
            if (requestCode == 1)
            {
                Stream imageStream = ContentResolver.OpenInputStream(data.Data);
                if (callback != null)
                {
                    callback(imageStream);
                }
            }
        }
        protected override void OnCreate(Bundle savedInstanceState)
        {
            AndroidPickPhoto.mainActivity = this;
            TabLayoutResource = Resource.Layout.Tabbar;
            ToolbarResource = Resource.Layout.Toolbar;

            base.OnCreate(savedInstanceState);

            Xamarin.Essentials.Platform.Init(this, savedInstanceState);
            global::Xamarin.Forms.Forms.Init(this, savedInstanceState);
            LoadApplication(new App());
        }
        public override void OnRequestPermissionsResult(int requestCode, string[] permissions, [GeneratedEnum] Android.Content.PM.Permission[] grantResults)
        {
            Xamarin.Essentials.Platform.OnRequestPermissionsResult(requestCode, permissions, grantResults);

            base.OnRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}