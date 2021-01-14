package com.example.youviewexercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.person.apply {
            detail_person_name.text = view.context.getString(R.string.full_name, title, firstName, lastName)
            detail_person_email.text = email
            location.apply {
                detail_person_street.text = view.context.getString(
                    R.string.house_number_and_street, streetNumber,
                    streetName
                )
                detail_person_city.text = city
                detail_person_state.text = state
                detail_person_postcode.text = postcode
                detail_person_country.text = country
            }

            Picasso.get()
                .load(largeUrl)
                .resize(128, 128)
                .centerCrop()
                .placeholder(R.drawable.ic_person)
                .error(R.drawable.ic_error)
                .into(detail_person_image)
        }
    }
}