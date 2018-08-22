package com.github.satoshun.example.motif

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main_act)

    val rootScope = RootFactoryImpl().create(this)

    rootScope.context()
    rootScope.viewWrapper()
  }
}

@motif.Scope
interface RootFactory {
  fun create(context: Context): RootScope

  @motif.Dependencies
  interface Dependencies

  @motif.Objects
  abstract class Objects {
    abstract val d: Dummy
  }
}

@motif.Scope
interface RootScope {
  fun context(): Context
  fun viewWrapper(): ViewWrapper

  @motif.Objects
  abstract class Objects {
    abstract fun wrapper(): ViewWrapper

    fun view(context: Context): View = View(context)
  }
}

class ViewWrapper(val view: View)

class Dummy
