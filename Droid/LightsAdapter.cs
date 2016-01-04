
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using Android.App;
using Android.Content;
using Android.OS;
using Android.Runtime;
using Android.Views;
using Android.Widget;

namespace Core.Droid
{		
	public class LightsAdapter : BaseAdapter<Core.Droid.LightsAdapter.ColorItem>
	{
		public LightsAdapter(Context context, List<ColorItem> items){
			_context = context;
			Items = items;
		}

		Context _context;
		List<ColorItem> Items { get; set;}

		#region implemented abstract members of BaseAdapter
		public override long GetItemId (int position)
		{
			return position;
		}

		public override int Count {
			get {
				return this.Items.Count;
			}
		}
		#endregion
		#region implemented abstract members of BaseAdapter
		public override ColorItem this [int index] {
			get {
				return Items.ElementAt (index);
			}
		}
		public  ColorItem GetItem(int i) {
			return Items.ElementAt (i);
		}
		#endregion
		public override View GetView (int position, View convertView, ViewGroup parent)
		{
			View v = convertView;
			ImageView picture;
			TextView name;

			if (v == null) {
				v = LayoutInflater.From(_context).Inflate(Resource.Layout.ColorItem, null, false);
			//	v.SetTag(Resource.Id.picture, v.FindViewById(Resource.Id.picture));
			//	v.SetTag(Resource.Id.text, v.FindViewById(Resource.Id.text));
			}

		//	picture = (ImageView) v.GetTag(Resource.Id.picture);
		//	name = (TextView) v.GetTag(Resource.Id.text);

			ColorItem item = GetItem(position);

			//picture.SetImageResource(item.DrawableId);
			//name.SetText(item.Name);

			return v;
		}

		public class ColorItem {
			public String Name;
			public int DrawableId;

			ColorItem(String name, int drawableId) {
				this.Name = name;
				this.DrawableId = drawableId;
			}
		}
	}
}

