package com.magsala.app
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
class MainActivity:ComponentActivity(){override fun onCreate(b:Bundle?){super.onCreate(b);setContent{MagsalaApp()}}}
@OptIn(ExperimentalMaterial3Api::class)
@Composable fun MagsalaApp(){val primary=Color(0xFF6750A4);val container=Color(0xFFEADDFF)
MaterialTheme(colorScheme=lightColorScheme(primary=primary,primaryContainer=container)){var sel by remember{mutableStateOf(0)}
Scaffold(topBar={TopAppBar(title={Text("مغسلة السيارات")},colors=TopAppBarDefaults.topAppBarColors(containerColor=container))},bottomBar={NavigationBar{NavigationBarItem(sel==0,{sel=0},{Icon(Icons.Default.Home,null)},{Text("الرئيسية")});NavigationBarItem(sel==1,{sel=1},{Icon(Icons.Default.Search,null)},{Text("بحث")});NavigationBarItem(sel==2,{sel=2},{Icon(Icons.Default.Favorite,null)},{Text("المحفوظ")});NavigationBarItem(sel==3,{sel=3},{Icon(Icons.Default.Settings,null)},{Text("الإعدادات")})}},floatingActionButton={FloatingActionButton(onClick={},containerColor=primary){Icon(Icons.Default.Add,null,tint=Color.White)}}){p->LazyColumn(Modifier.padding(p).padding(16.dp),verticalArrangement=Arrangement.spacedBy(16.dp)){item{Row{ FilledTonalButton({}){Icon(Icons.Default.Favorite,null);Spacer(Modifier.width(8.dp));Text("المفضلة")}; Spacer(Modifier.width(8.dp)); FilledTonalButton({}){Icon(Icons.Default.Share,null);Spacer(Modifier.width(8.dp));Text("مشاركة")} } };item{CardC("غسيل خارجي","30 ريال","سريع",primary)};item{CardC("غسيل داخلي وخارجي","50 ريال","شامل",primary)};item{CardC("تلميع كامل","120 ريال","بوليش",primary)}}}}}
@Composable fun CardC(t:String,pr:String,d:String,primary:Color){ElevatedCard(Modifier.fillMaxWidth()){Column(Modifier.padding(16.dp),verticalArrangement=Arrangement.spacedBy(8.dp)){Text(t,style=MaterialTheme.typography.titleLarge);Text(pr,color=primary);Text(d);Button(onClick={},colors=ButtonDefaults.buttonColors(containerColor=primary)){Text("احجز الآن")}}}}
