using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;

namespace Xamarin_Example
{
    public delegate void PhotoResult(Stream imageStream);
    public interface IPhotoPicker
    {
        void SetPhotoCallback(PhotoResult cb);
        void PickPhoto();
    }
    public partial class MainPage : ContentPage
    {
        public MainPage()
        {
            InitializeComponent();
        }

        private void Button_Clicked(object sender, EventArgs e)
        {
            labelResult.Text += ((Button)sender).Text;
            var photoPicker = DependencyService.Get<IPhotoPicker>();
            photoPicker.SetPhotoCallback(PhotoResult);
            photoPicker.PickPhoto();
        }
        void PhotoResult(Stream imageStream)
        {
            imageResult.Source = ImageSource.FromStream(() => { return imageStream; });
        }
    }
}
