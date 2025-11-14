package com.example.kotlin_control // Or your package name

// All the imports needed
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
// --- NEW IMPORTS FOR DIALOG & TEXT FIELDS ---
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextButton
// --- END NEW IMPORTS ---
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
// --- IMPORTS FOR STATE ---
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
// --- END IMPORTS ---
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// This annotation is needed for FlowRow
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProfileScreen() {

    // --- COLOR PALETTE ---
    val backgroundColor = Color(0xFFFAFAFA) // Very light off-white
    val accentColor = Color(0xFF0D47A1) // A deep, professional blue
    val primaryTextColor = Color(0xFF212121) // Near-black
    val secondaryTextColor = Color(0xFF757575) // A medium gray for bio/location
    val chipColor = Color(0xFFE0E0E0) // Light gray for chips
    val cardBackgroundColor = Color.White // Cards will be white

    // --- STATE VARIABLES ---
    // These hold the "source of truth" for your profile data
    var name by remember { mutableStateOf("Gift") }
    var bio by remember { mutableStateOf("@l.1wuu | Android Developer") }
    var location by remember { mutableStateOf("Nairobi") }

    // This state variable controls if the edit dialog is shown or not
    var showEditDialog by remember { mutableStateOf(false) }

    // --- Main column, now scrollable ---
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        // --- Top Padding ---
        Spacer(modifier = Modifier.height(40.dp))

        // --- Profile Image ---
        Image(
            painter = painterResource(id = R.drawable.ben), // Your image
            contentDescription = "Profile picture",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .border(
                    width = 4.dp,
                    color = accentColor,
                    shape = CircleShape
                )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // --- User Info ---
        Text(
            text = name, // <-- Uses state
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Cursive,
            color = primaryTextColor
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = bio, // <-- Uses state
            fontSize = 16.sp,
            color = secondaryTextColor
        )
        Spacer(modifier = Modifier.height(8.dp))

        // --- Location ---
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "Location",
                tint = secondaryTextColor,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = location, // <-- Uses state
                fontSize = 16.sp,
                color = secondaryTextColor
            )
        }

        // --- EDIT PROFILE BUTTON ---
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                // When clicked, just show the dialog
                showEditDialog = true
            },
            colors = ButtonDefaults.buttonColors(containerColor = accentColor),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "Edit Profile")
        }
        // --- END EDIT BUTTON ---

        // --- Space before cards ---
        Spacer(modifier = Modifier.height(24.dp))

        // --- Languages Section (NOW A CARD) ---
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp), // Give card horizontal margin
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = cardBackgroundColor),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp), // Inner padding for card content
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Languages",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = accentColor
                )
                Spacer(modifier = Modifier.height(16.dp))
                FlowRow(
                    modifier = Modifier.padding(horizontal = 16.dp), // Padding for the chips
                    horizontalArrangement = Arrangement.Center
                ) {
                    LanguageChip(name = "Kotlin", color = chipColor, textColor = primaryTextColor)
                    LanguageChip(name = "Java", color = chipColor, textColor = primaryTextColor)
                    LanguageChip(name = "Python", color = chipColor, textColor = primaryTextColor)
                    LanguageChip(name = "C", color = chipColor, textColor = primaryTextColor)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp)) // Space between cards

        // --- Socials Section (NOW A CARD) ---
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp), // Give card horizontal margin
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = cardBackgroundColor),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp), // Inner padding for card content
                horizontalAlignment = Alignment.CenterHorizontally // Center title
            ) {
                Text(
                    text = "Socials",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = accentColor
                )
                Spacer(modifier = Modifier.height(16.dp))
                val context = LocalContext.current
                Column(
                    modifier = Modifier.padding(horizontal = 24.dp), // Padding for social items
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    SocialItem(
                        icon = painterResource(id = R.drawable.sufer), // Your icon
                        text = "0745165115",
                        iconColor = accentColor,
                        textColor = secondaryTextColor,
                        onClick = {
                            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:0745165115"))
                            context.startActivity(intent)
                        }
                    )
                    SocialItem(
                        icon = painterResource(id = R.drawable.google), // Your icon
                        text = "mutisogift2@gmail.com",
                        iconColor = Color.Unspecified,
                        textColor = secondaryTextColor,
                        onClick = {
                            val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:mutisogift2@gmail.com"))
                            context.startActivity(intent)
                        }
                    )
                    SocialItem(
                        icon = painterResource(id = R.drawable.github), // Your icon
                        text = "MutuaGift",
                        iconColor = Color.Unspecified,
                        textColor = secondaryTextColor,
                        onClick = {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/MutuaGift"))
                            context.startActivity(intent)
                        }
                    )
                    SocialItem(
                        icon = painterResource(id = R.drawable.instagram), // Your icon
                        text = "l.1wuu",
                        iconColor = Color.Unspecified,
                        textColor = secondaryTextColor,
                        onClick = {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/l.1wuu"))
                            context.startActivity(intent)
                        }
                    )
                }
            }
        }

        // --- Bottom Padding ---
        Spacer(modifier = Modifier.height(40.dp))
    }

    // --- NEW EDIT PROFILE DIALOG ---
    // This will only be "composed" (built) if showEditDialog is true
    if (showEditDialog) {
        // These temporary states hold the text field inputs
        // They start with the *current* profile info
        var tempName by remember { mutableStateOf(name) }
        var tempBio by remember { mutableStateOf(bio) }
        var tempLocation by remember { mutableStateOf(location) }

        AlertDialog(
            onDismissRequest = { showEditDialog = false }, // Close if user clicks outside
            title = { Text("Edit Profile") },
            text = {
                // A column to hold our text fields
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(
                        value = tempName,
                        onValueChange = { tempName = it },
                        label = { Text("Name") }
                    )
                    OutlinedTextField(
                        value = tempBio,
                        onValueChange = { tempBio = it },
                        label = { Text("Bio") }
                    )
                    OutlinedTextField(
                        value = tempLocation,
                        onValueChange = { tempLocation = it },
                        label = { Text("Location") }
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        // --- THIS IS THE KEY ---
                        // On "Save", update the *real* state variables
                        // with the temporary ones.
                        name = tempName
                        bio = tempBio
                        location = tempLocation
                        // And close the dialog
                        showEditDialog = false
                    }
                ) {
                    Text("Save")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        // Just close the dialog, changes are discarded
                        showEditDialog = false
                    }
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}

// --- Helper Composable 1: LanguageChip ---
@Composable
fun LanguageChip(name: String, color: Color, textColor: Color) {
    Surface(
        modifier = Modifier.padding(4.dp),
        shape = RoundedCornerShape(8.dp),
        color = color,
        contentColor = textColor
    ) {
        Text(
            text = name,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

// --- Helper Composable 2: SocialItem (Simple Version) ---
@Composable
fun SocialItem(
    icon: Painter,
    text: String,
    iconColor: Color,
    textColor: Color,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 4.dp) // Smaller padding for list items
    ) {
        Icon(
            painter = icon,
            contentDescription = text,
            tint = iconColor, // Use the provided icon color
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            fontSize = 16.sp,
            color = textColor // Use the provided text color
        )
    }
}

// --- Preview Function ---
@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}