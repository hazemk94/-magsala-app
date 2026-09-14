package com.magsala.app

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class CarService(val name: String, val desc: String, val price: String, val icon: ImageVector)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { CarWashApp() }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarWashApp() {
    val primaryBlue = Color(0xFF0D47A1)
    val accentOrange = Color(0xFFFF6F00)
    val context = LocalContext.current
    var selectedTab by remember { mutableStateOf(0) }

    val services = listOf(
        CarService("غسيل خارجي", "غسيل + تنشيف + ملمع كفرات", "30 ر.س", Icons.Filled.Refresh),
        CarService("داخلي وخارجي", "غسيل شامل + تنظيف داخلي + تعطير", "60 ر.س", Icons.Filled.CheckCircle),
        CarService("تلميع كامل", "تلميع بودي + واكس حماية", "150 ر.س", Icons.Filled.Star),
        CarService("غسيل محرك", "تنظيف محرك بالبخار", "40 ر.س", Icons.Filled.Settings),
        CarService("تنظيف مقاعد جلد", "تنظيف وتعقيم المقاعد", "50 ر.س", Icons.Filled.Favorite),
        CarService("باقة VIP", "غسيل شامل + تلميع + تعطير فاخر", "199 ر.س", Icons.Filled.Person)
    )

    fun openWhatsApp(msg: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/966500000000?text=$msg"))
        context.startActivity(intent)
    }

    MaterialTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(modifier = Modifier.size(40.dp).clip(RoundedCornerShape(10.dp)).background(Color.White), contentAlignment = Alignment.Center) {
                                Text("🚗", fontSize = 24.sp)
                            }
                            Spacer(Modifier.width(10.dp))
                            Column {
                                Text("مغسلة أبو هلال", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                                Text("لغسيل السيارات", fontSize = 12.sp, color = Color.White.copy(0.8f))
                            }
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = primaryBlue, titleContentColor = Color.White)
                )
            },
            bottomBar = {
                NavigationBar {
                    NavigationBarItem(selected = selectedTab==0, onClick = {selectedTab=0}, icon={Icon(Icons.Filled.Home,null)}, label={Text("الرئيسية")})
                    NavigationBarItem(selected = selectedTab==1, onClick = {selectedTab=1}, icon={Icon(Icons.Filled.List,null)}, label={Text("عروضنا")})
                    NavigationBarItem(selected = selectedTab==2, onClick = {selectedTab=2}, icon={Icon(Icons.Filled.Person,null)}, label={Text("حسابي")})
                }
            }
        ) { padding ->
            Box(Modifier.padding(padding)) {
                when(selectedTab) {
                    0 -> LazyColumn(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        item {
                            Card(modifier=Modifier.fillMaxWidth(), colors=CardDefaults.cardColors(containerColor=accentOrange), shape=RoundedCornerShape(16.dp)) {
                                Column(Modifier.padding(20.dp)) {
                                    Text("🔥 عرض الافتتاح", color=Color.White, fontWeight=FontWeight.Bold, fontSize=20.sp)
                                    Text("غسلتين والثالثة مجاناً + تعطير مجاني", color=Color.White, fontSize=15.sp)
                                    Spacer(Modifier.height(12.dp))
                                    Button(onClick={openWhatsApp("ابي عرض الغسلتين")}, colors=ButtonDefaults.buttonColors(containerColor=Color.White)) {
                                        Text("احجز الآن واتساب", color=accentOrange, fontWeight=FontWeight.Bold)
                                    }
                                }
                            }
                        }
                        item { Text("خدماتنا", fontWeight=FontWeight.Bold, fontSize=20.sp, modifier=Modifier.padding(top=8.dp)) }
                        items(services) { s ->
                            Card(Modifier.fillMaxWidth(), elevation=CardDefaults.cardElevation(2.dp)) {
                                Row(Modifier.fillMaxWidth().padding(16.dp), verticalAlignment=Alignment.CenterVertically) {
                                    Box(Modifier.size(48.dp).clip(RoundedCornerShape(12.dp)).background(primaryBlue.copy(0.1f)), contentAlignment=Alignment.Center) {
                                        Icon(s.icon, null, tint=primaryBlue)
                                    }
                                    Spacer(Modifier.width(12.dp))
                                    Column(Modifier.weight(1f)) {
                                        Text(s.name, fontWeight=FontWeight.Bold, fontSize=16.sp)
                                        Text(s.desc, fontSize=12.sp, color=Color.Gray)
                                        Text(s.price, fontWeight=FontWeight.Bold, color=accentOrange, fontSize=16.sp)
                                    }
                                    Button(onClick={openWhatsApp("ابي ${s.name}")}, colors=ButtonDefaults.buttonColors(containerColor=primaryBlue)) {
                                        Text("اطلب")
                                    }
                                }
                            }
                        }
                    }
                    1 -> LazyColumn(Modifier.fillMaxSize().padding(16.dp), verticalArrangement=Arrangement.spacedBy(12.dp)) {
                        item { Text("العروض والباقات", fontWeight=FontWeight.Bold, fontSize=22.sp) }
                        item {
                            Card(colors=CardDefaults.cardColors(containerColor=Color(0xFFE3F2FD))) {
                                Column(Modifier.padding(16.dp)) {
                                    Text("باقة شهرية - 4 غسلات", fontWeight=FontWeight.Bold)
                                    Text("199 ر.س بدل 240 ر.س", color=primaryBlue, fontWeight=FontWeight.Bold, fontSize=18.sp)
                                    Spacer(Modifier.height(8.dp))
                                    Button(onClick={openWhatsApp("ابي الباقة الشهرية")}) { Text("اشترك") }
                                }
                            }
                        }
                        item {
                            Card(colors=CardDefaults.cardColors(containerColor=Color(0xFFFFF3E0))) {
                                Column(Modifier.padding(16.dp)) {
                                    Text("عرض العائلة - سيارتين", fontWeight=FontWeight.Bold)
                                    Text("99 ر.س بدل 120 ر.س", color=accentOrange, fontWeight=FontWeight.Bold, fontSize=18.sp)
                                }
                            }
                        }
                    }
                    2 -> Column(Modifier.fillMaxSize().padding(20.dp), horizontalAlignment=Alignment.CenterHorizontally) {
                        Box(Modifier.size(80.dp).clip(RoundedCornerShape(40.dp)).background(primaryBlue.copy(0.1f)), contentAlignment=Alignment.Center) {
                            Icon(Icons.Filled.Person, null, tint=primaryBlue, modifier=Modifier.size(40.dp))
                        }
                        Spacer(Modifier.height(12.dp))
                        Text("أهلاً بك في مغسلة أبو هلال", fontWeight=FontWeight.Bold, fontSize=18.sp)
                        Spacer(Modifier.height(16.dp))
                        Card(Modifier.fillMaxWidth()) {
                            Column(Modifier.padding(16.dp), verticalArrangement=Arrangement.spacedBy(8.dp)) {
                                Text("🚗 سيارتي: كامري 2022", textAlign=TextAlign.Right, modifier=Modifier.fillMaxWidth())
                                Text("⭐ نقاطي: 120 نقطة", textAlign=TextAlign.Right, modifier=Modifier.fillMaxWidth())
                                Text("🎁 رصيد العروض: غسلة مجانية متاحة", textAlign=TextAlign.Right, modifier=Modifier.fillMaxWidth())
                            }
                        }
                    }
                }
            }
        }
    }
}
