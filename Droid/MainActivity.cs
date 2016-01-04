using System;

using Android.App;
using Android.Content;
using Android.Content.PM;
using Android.Runtime;
using Android.Views;
using Android.Widget;
using Android.OS;
using System.Collections.Generic;
using Android.Graphics;

namespace Core.Droid
{
	[Activity (Label = "Core.Droid", Icon = "@drawable/icon", MainLauncher = true, ConfigurationChanges = ConfigChanges.ScreenSize | ConfigChanges.Orientation)]
	public class MainActivity : global::Xamarin.Forms.Platform.Android.FormsApplicationActivity
	{

		bool IsRed; 
		protected override void OnCreate (Bundle bundle)
		{
			base.OnCreate (bundle);

			global::Xamarin.Forms.Forms.Init (this, bundle);
			var app = new App ();
			LoadApplication(app);
			this.SetContentView(Resource.Layout.Main);
			//var lightSignalsView = FindViewById<GridView> (Resource.Id.LightSignal);
			//lightSignalsView.Adapter = new LightsAdapter(this,new List<Item>{new Item("Red",       Resource.Drawable.)})

			System.Timers.Timer timer = new System.Timers.Timer();
			timer.Interval = 1000; 
			timer.Elapsed += ColorFlip;
			timer.Enabled = true;
				//lightSignalsView.Adapter = new ArrayAdapter<string> (this,Android.Resource.Layout.SimpleListItem1, new List<string>{"A", "B", "C"});

		}

		private void ColorFlip(object sender, System.Timers.ElapsedEventArgs e)
		{
			RunOnUiThread (() => {
			var left = FindViewById<TextView> (Resource.Id.Left);
			var right = FindViewById<TextView> (Resource.Id.Right);

			if (IsRed) {
				left.SetBackgroundColor (Color.Black);
				right.SetBackgroundColor (Color.Blue);
				IsRed = false;
			}else {
				left.SetBackgroundColor (Color.Red);
				right.SetBackgroundColor (Color.Black);
				IsRed = true;
			}
			});
		}
	}
}

