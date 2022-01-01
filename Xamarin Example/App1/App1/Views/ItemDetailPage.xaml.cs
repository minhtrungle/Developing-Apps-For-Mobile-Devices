using System.ComponentModel;
using Xamarin.Forms;
using App1.ViewModels;

namespace App1.Views
{
    public partial class ItemDetailPage : ContentPage
    {
        public ItemDetailPage()
        {
            InitializeComponent();
            BindingContext = new ItemDetailViewModel();
        }
    }
}