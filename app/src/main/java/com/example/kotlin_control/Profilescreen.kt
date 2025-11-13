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
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
    val borderColor = Color.LightGray

    // Main column, now scrollable
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor) // --- NEW COLOR ---
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
                    color = accentColor, // --- NEW COLOR ---
                    shape = CircleShape
                )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // --- User Info ---
        Text(
            text = "Gift",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Cursive,
            color = primaryTextColor // --- NEW COLOR ---
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "@l.1wuu | Android Developer", // <-- UPDATED TEXT
            fontSize = 16.sp,
            color = secondaryTextColor // --- NEW COLOR ---
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
                tint = secondaryTextColor, // --- NEW COLOR ---
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Nairobi",
                fontSize = 16.sp,
                color = secondaryTextColor // --- NEW COLOR ---
            )
        }

        // --- Divider ---
        Spacer(modifier = Modifier.height(24.dp))
        Divider(color = borderColor, thickness = 1.dp, modifier = Modifier.padding(horizontal = 32.dp))
        Spacer(modifier = Modifier.height(24.dp))

        // --- Languages Section ---
        Text(
            text = "Languages",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = accentColor // --- NEW COLOR ---
        )
        Spacer(modifier = Modifier.height(16.dp))
        FlowRow(
            modifier = Modifier
                .padding(horizontal = 32.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            LanguageChip(name = "Kotlin", color = chipColor, textColor = primaryTextColor)
            LanguageChip(name = "Java", color = chipColor, textColor = primaryTextColor)
            LanguageChip(name = "Python", color = chipColor, textColor = primaryTextColor)
            LanguageChip(name = "C", color = chipColor, textColor = primaryTextColor)
        }

        // --- Divider ---
        Spacer(modifier = Modifier.height(24.dp))
        Divider(color = borderColor, thickness = 1.dp, modifier = Modifier.padding(horizontal = 32.dp))
        Spacer(modifier = Modifier.height(24.dp))

        // --- Socials Section (Now Clickable) ---
        Text(
            text = "Socials",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = accentColor // --- NEW COLOR ---
        )
        Spacer(modifier = Modifier.height(16.dp))
        val context = LocalContext.current
        Column(
            modifier = Modifier.padding(horizontal = 40.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            SocialItem(
                icon = painterResource(id = R.drawable.sufer), // Your icon
                text = "0745165115",
                iconColor = accentColor, // We WANT this one tinted blue
                textColor = secondaryTextColor,
                onClick = {
                    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:0745165115"))
                    context.startActivity(intent)
                }
            )
            SocialItem(
                icon = painterResource(id = R.drawable.google), // Your icon
                text = "mutisogift2@gmail.com",
                iconColor = Color.Unspecified, // <-- THIS IS THE FIX
                textColor = secondaryTextColor,
                onClick = {
                    val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:mutisogift2@gmail.com"))
                    context.startActivity(intent)
                }
            )
            SocialItem(
                icon = painterResource(id = R.drawable.github), // Your icon
                text = "MutuaGift",
                iconColor = Color.Unspecified, // <-- THIS IS THE FIX
                textColor = secondaryTextColor,
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/MutuaGift"))
                    context.startActivity(intent)
                }
            )
            SocialItem(
                icon = painterResource(id = R.drawable.instagram), // Your icon
                text = "l.1wuu", // <-- UPDATED TEXT
                iconColor = Color.Unspecified, // <-- THIS IS THE FIX
                textColor = secondaryTextColor,
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/l.1wuu"))
                    context.startActivity(intent)
                }
            )
        }
        Spacer(modifier = Modifier.height(40.dp))
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
            .padding(vertical = 4.dp)
    ) {
        Icon(
            painter = icon,
            contentDescription = text,
            tint = iconColor, // Use the provided icon color
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp)) // <-- TYPO IS FIXED HERE
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