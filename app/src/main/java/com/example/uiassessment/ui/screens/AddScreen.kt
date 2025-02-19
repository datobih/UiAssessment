package com.example.uiassessment.ui.screens

import android.Manifest
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.READ_MEDIA_IMAGES
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest
import com.example.lostandfound.utils.UIState
import com.example.uiassessment.R
import com.example.uiassessment.createImageUri

import com.example.uiassessment.getFileFromUri
import com.example.uiassessment.models.FoodRequestDTO
import com.example.uiassessment.navigation.HomeRef
import com.example.uiassessment.openCamera
import com.example.uiassessment.ui.CustomDescriptionTextField
import com.example.uiassessment.ui.CustomMenuTextField
import com.example.uiassessment.ui.CustomTextField
import com.example.uiassessment.ui.TagsField
import com.example.uiassessment.ui.theme.LocalFonts
import com.example.uiassessment.ui.theme.borderGrey
import com.example.uiassessment.ui.theme.darkBlue
import com.example.uiassessment.ui.theme.disabledButtonColor
import com.example.uiassessment.ui.theme.headerCircle
import com.example.uiassessment.ui.theme.highlightBlue
import com.example.uiassessment.ui.theme.smallTextLight
import com.example.uiassessment.viewmodel.MainViewModel
import java.io.File

@Composable
fun AddScreen(mainViewModel: MainViewModel,navHostController: NavHostController){

    var foodNameState by remember { mutableStateOf("") }
    var descriptionState by remember { mutableStateOf("") }
    var categoryState by remember { mutableStateOf("") }
    var caloriesState by remember { mutableStateOf("") }
    val tags = remember { mutableStateListOf<String>() }

    val imageFileList = remember { mutableStateListOf<File>() }
    val isInputValid = (foodNameState.isNotEmpty() && descriptionState.isNotEmpty()
            && categoryState.isNotEmpty()&& caloriesState.isNotEmpty() && tags.isNotEmpty() && imageFileList.isNotEmpty())

    val context = LocalContext.current

    val capturedImageUri by remember { mutableStateOf( createImageUri(context))}


    val galleryLauncher = rememberLauncherForActivityResult(
        contract =
        ActivityResultContracts.GetContent()
    ) { uri: Uri?->

        uri?.let {

            getFileFromUri(context,uri)?.let { it1 -> imageFileList.add(it1) }
        }
    }

    val cameraLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) {

            getFileFromUri(context,capturedImageUri)?.let { imageFileList.add(it) }


            }
         else {
            println("Image capture failed")
        }
    }

    //  Permission Launcher
    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // Permission Granted, proceed to open camera
            openCamera(context, capturedImageUri,cameraLauncher,)
        } else {

            println("Camera permission denied")

        }
    }

    val readPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // Permission Granted, proceed to open camera
           galleryLauncher.launch("image/*")
        } else {

            println("Read permission denied")

        }
    }

val createFoodState by mainViewModel.createFoodRequestLiveData.observeAsState()


    Column() {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
                AddNewFoodHeader()

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                ) {
                    Column(modifier = Modifier
                        .border(1.dp, headerCircle, RoundedCornerShape(4.dp))
                        .clickable {
                            if (context.checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                                // Permission already granted, open camera directly
                                openCamera(context, capturedImageUri, cameraLauncher)
                            } else {
                                // Permission not granted, request it
                                cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
                            }
                        }
                        .weight(1f)
                        .padding(vertical = 20.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Icon(
                            painter = painterResource(R.drawable.camera),
                            contentDescription = "Back",
                            tint = darkBlue,
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            "Take photo",
                            style = LocalFonts.current.bodyRegular,
                            modifier = Modifier.padding(top = 4.dp)
                        )


                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Column(modifier = Modifier
                        .border(1.dp, headerCircle, RoundedCornerShape(4.dp))
                        .clickable {

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                                if (context.checkSelfPermission(READ_MEDIA_IMAGES) == PackageManager.PERMISSION_GRANTED) {
                                    galleryLauncher.launch("image/*")
                                } else {
                                    readPermissionLauncher.launch(READ_MEDIA_IMAGES)
                                }

                            } else {
                                if (context.checkSelfPermission(READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                                    galleryLauncher.launch("image/*")
                                } else {
                                    readPermissionLauncher.launch(READ_EXTERNAL_STORAGE)
                                }

                            }

                        }
                        .weight(1f)
                        .padding(vertical = 20.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Icon(
                            painter = painterResource(R.drawable.upload),
                            contentDescription = "Back",
                            tint = darkBlue,
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            "Take photo",
                            style = LocalFonts.current.bodyRegular,
                            modifier = Modifier.padding(top = 4.dp)
                        )


                    }

                }


                if (imageFileList.isNotEmpty()) {
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 3.dp, start = 16.dp, end = 16.dp)
                    ) {

                        items(imageFileList.toList()) { uri ->


                            Box(modifier = Modifier.size(50.dp)) {
                                SubcomposeAsyncImage(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(4.dp))
                                        .fillMaxSize(), loading = {
                                        Box(
                                            modifier = Modifier.fillMaxWidth(),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            CircularProgressIndicator(
                                                color = Color.Black,
                                                modifier = Modifier.width(20.dp)
                                            )
                                        }
                                    }, model = ImageRequest.Builder(LocalContext.current)
                                        .data(uri).build(), contentDescription = "Image",
                                    contentScale = ContentScale.Crop
                                )


                                Image(painter = painterResource(R.drawable.image_cancel),
                                    modifier = Modifier
                                        .padding(end = 4.dp, bottom = 4.dp)
                                        .clickable { imageFileList.remove(uri) }
                                        .size(16.dp)
                                        .align(Alignment.BottomEnd),
                                    contentDescription = "")
                            }
                            Spacer(modifier = Modifier.width(3.dp))
                        }

                    }

                }

                Spacer(modifier = Modifier.height(16.dp))




                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = "Name",
                        style = LocalFonts.current.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    CustomTextField(
                        foodNameState, "Enter food name",
                        { text ->
                            foodNameState = text
                        }, modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Description",
                        style = LocalFonts.current.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    CustomDescriptionTextField(
                        descriptionState, "Enter food description",
                        { text -> descriptionState = text },
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Category",
                        style = LocalFonts.current.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    CustomMenuTextField(categoryState, "Enter Category") {
                        categoryState = it
                    }

                    Spacer(modifier = Modifier.height(16.dp))


                    Text(
                        text = "Calories",
                        style = LocalFonts.current.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    CustomTextField(
                        caloriesState, "Enter number of calories",
                        { text -> caloriesState = text }, modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Tags",
                        style = LocalFonts.current.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    TagsField(tags)
                }


                Button(
                    onClick = {

                        mainViewModel.createFoodRequest(FoodRequestDTO(name = foodNameState,description = descriptionState,
                            calories = caloriesState, tags = tags, images = imageFileList, categoryId = categoryState))

                    },
                    colors = ButtonColors(
                        containerColor = highlightBlue,
                        contentColor = Color.White,
                        disabledContainerColor = disabledButtonColor,
                        disabledContentColor = smallTextLight
                    ),
                    enabled = isInputValid,
                    modifier = Modifier
                        .padding(top = 80.dp, bottom = 16.dp)
                        .fillMaxWidth(.93f)
                        .align(Alignment.CenterHorizontally),
                    shape = RoundedCornerShape(4.dp)
                ) {

                    Text(
                        "Add food",
                        modifier = Modifier.padding(vertical = 6.dp),
                        style = if (isInputValid) LocalFonts.current.bodyRegularWhite else LocalFonts.current.bodyRegularLightAlt
                    )

                }
            }
            when(createFoodState){

                is UIState.LoadingState -> { Box(modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0x4BFFFFFF)).clickable{

                    }, contentAlignment = Alignment.Center){

                    CircularProgressIndicator(
                        color = Color.Black,
                        modifier = Modifier
                            .width(40.dp)
                            .align(Alignment.Center)
                    )


                }}
                is UIState.ErrorState -> {
                    LaunchedEffect(true){
                        Toast.makeText(context, (createFoodState as UIState.ErrorState).data,Toast.LENGTH_SHORT).show()
                    }
                    }
                is UIState.SuccessState -> {

                    LaunchedEffect(true){
                        Toast.makeText(context, "Food Added",Toast.LENGTH_SHORT).show()
                    }

               LaunchedEffect(true) {
                   mainViewModel.getFoods()
                   navHostController.popBackStack()

                }


                }
                is UIState.InitialState -> {

                }
                null -> {

                }
            }



        }
    }


}







@Composable
fun AddNewFoodHeader() {
    Column(modifier = Modifier.fillMaxWidth()) { // Main Column to hold header and separator
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp), // Horizontal padding and vertical for top/bottom spacing
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Back Button (Icon Button with Circle Background)
            IconButton(
                onClick = { /*TODO: Handle back navigation*/ },
                modifier = Modifier
                    .border(1.dp, headerCircle, CircleShape)
                    .size(40.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.arrow_square_back),
                    contentDescription = "Back",
                    tint = Color.Black,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))


            Text(
                text = "Add new food",
                style = LocalFonts.current.title
            )
        }

        // Separator Line
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(borderGrey)

        )




    }
}
