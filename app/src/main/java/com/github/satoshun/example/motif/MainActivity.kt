package com.github.satoshun.example.motif

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.github.satoshun.example.motif.databinding.MainActBinding
import motif.Expose

class MainActivity : AppCompatActivity() {
  private lateinit var binding: MainActBinding

  private val rootScope by lazy { RootFactoryImpl().create(this) }
  private val context by lazy { rootScope.context() }
  private val viewWrapper by lazy { rootScope.viewWrapper() }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.main_act)

    val child = rootScope.create()
  }
}

@motif.Scope
interface RootFactory {
  fun create(context: Context): RootScope

  @motif.Dependencies
  interface Dependencies

  @motif.Objects
  abstract class Objects {
    @get:Expose abstract val d: Dummy
  }
}

@motif.Scope
interface RootScope {
  fun create(): ChildScope

  fun context(): Context
  fun viewWrapper(): ViewWrapper

  @motif.Objects
  abstract class Objects {
    @Expose abstract fun wrapper(): ViewWrapper

    fun view(context: Context): View = View(context)
  }
}

class ViewWrapper(val view: View, val dummy: Dummy)

@motif.Scope
interface ChildScope {
  @motif.Objects
  abstract class Objects {
    abstract fun childDummy(): ChildDummy
  }
}

class ChildDummy(val wrapper: ViewWrapper)

class Dummy
//class Dummy(private val i: Int)
