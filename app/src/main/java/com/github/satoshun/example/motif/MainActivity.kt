package com.github.satoshun.example.motif

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main_act)

    RootFactoryImpl().create(this)
  }
}

@motif.Scope
interface RootFactory {
  fun create(context: Context): RootScope

  @motif.Dependencies
  interface Dependencies
}

@motif.Scope
interface RootScope {
  @motif.Objects
  abstract class Objects
}
