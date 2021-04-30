package com.sayantanbanerjee.transactionmanagementapp.presenter.AddRecordActivityPackage

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import com.sayantanbanerjee.transactionmanagementapp.R
import com.sayantanbanerjee.transactionmanagementapp.data.preference.AppPreferenceHelper
import com.sayantanbanerjee.transactionmanagementapp.databinding.ActivityAddRecordBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AddRecordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddRecordBinding

    @Inject
    lateinit var sharedPreferences: SharedPreferences

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
            var qrParity: Int = 0
            if (binding.parityGroup.checkedRadioButtonId == R.id.amountReceivedButton) {
                parity = 0
                qrParity = 1
            }

            if (phoneNumber == "" || amount == "") {
                Toast.makeText(this, "Fields cannot be left empty!", Toast.LENGTH_SHORT).show()
            } else {

                viewModel.saveRecord(phoneNumber, amount.toInt(), parity)

                val minePhoneNumber = AppPreferenceHelper(sharedPreferences).getUserMobileNumber()
                var recordNumber = AppPreferenceHelper(sharedPreferences).getRecordNumber()

                var inputForQREncryption = ""
                inputForQREncryption += "QRTRANSACTION"
                inputForQREncryption += ";"
                inputForQREncryption += minePhoneNumber
                inputForQREncryption += ";"
                inputForQREncryption += phoneNumber
                inputForQREncryption += ";"
                inputForQREncryption += amount
                inputForQREncryption += ";"
                inputForQREncryption += qrParity.toString()
                inputForQREncryption += ";"
                inputForQREncryption += recordNumber

                recordNumber++
                AppPreferenceHelper(sharedPreferences).setRecordNumber(recordNumber)

                try {
                    val barcodeEncoder = BarcodeEncoder()
                    val bitmap = barcodeEncoder.encodeBitmap(
                        inputForQREncryption,
                        BarcodeFormat.QR_CODE,
                        600,
                        600
                    )
                    binding.qrCode.setImageBitmap(bitmap)
                    Toast.makeText(this, "QR Successfully generated", Toast.LENGTH_LONG).show()
                } catch (e: Exception) {
                    Toast.makeText(
                        this,
                        "Something went wrong in generating QR Code!",
                        Toast.LENGTH_LONG
                    ).show()
                }

            }
        }
    }
}
