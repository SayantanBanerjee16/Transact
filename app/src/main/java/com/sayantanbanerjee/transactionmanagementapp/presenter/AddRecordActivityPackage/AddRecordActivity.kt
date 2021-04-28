package com.sayantanbanerjee.transactionmanagementapp.presenter.AddRecordActivityPackage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.sayantanbanerjee.transactionmanagementapp.R
import com.sayantanbanerjee.transactionmanagementapp.databinding.ActivityAddRecordBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AddRecordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddRecordBinding

    @Inject
    lateinit var factory: AddRecordViewModelFactory

    lateinit var viewModel: AddRecordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_record)
        viewModel = ViewModelProvider(this, factory).get(AddRecordViewModel::class.java)

        binding.saveButton.setOnClickListener {
            val phoneNumber = binding.phoneNumber.text.toString()
            val amount = binding.amountInvolved.text.toString()
            var parity = 1
            if (binding.parityGroup.checkedRadioButtonId == R.id.amountReceivedButton) {
                parity = 0
            }

            if (phoneNumber == "" || amount == "") {
                Toast.makeText(this, "Fields cannot be left empty!", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.saveRecord(phoneNumber, amount.toInt(),  parity)
                Toast.makeText(this, "Saved in Database!", Toast.LENGTH_SHORT).show()
            }

        }
    }
}