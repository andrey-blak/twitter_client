package com.example.twitter.ui.tweets

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.example.twitter.R
import com.example.twitter.data.dto.Tweet
import kotlinx.android.synthetic.main.tweet_item.view.*
import java.text.SimpleDateFormat

class TweetsAdapter (
	private val context: Context
) : RecyclerView.Adapter<TweetsAdapter.TweetViewHolder>() {
	private val tweets = mutableListOf<Tweet>()

	override fun getItemCount(): Int {
		return tweets.size
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetViewHolder {
		val inflater = LayoutInflater.from(context)
		val itemView = inflater.inflate(TweetViewHolder.layoutResId, parent, false)
		return TweetViewHolder(itemView)
	}

	override fun onBindViewHolder(holder: TweetViewHolder, position: Int) {
		val tweet = tweets[position]
		holder.bind(tweet)
	}

	fun setItems(tweets: List<Tweet>) {
		this.tweets.clear()
		this.tweets.addAll(tweets)
		notifyDataSetChanged()
	}

	class TweetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		companion object {
			@LayoutRes
			public const val layoutResId = R.layout.tweet_item
		}

		fun bind(tweet: Tweet) {
			itemView.tweet_item_username.setText(tweet.username)
			val formattedTime = formatTime(tweet.postedTimeMs)
			itemView.tweet_item_date.setText(formattedTime)
			itemView.tweet_item_message.setText(tweet.message)
		}

		private fun formatTime(date: Long): String? {
			val formatter = SimpleDateFormat("HH:mm yyyy-MM-dd")
			return formatter.format(date)
		}
	}
}
