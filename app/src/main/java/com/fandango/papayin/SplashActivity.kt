package com.fandango.papayin

import android.content.Intent
import com.viksaa.sssplash.lib.activity.AwesomeSplash
import com.daimajia.androidanimations.library.Techniques
import com.fandango.papayin.presentation.BaseActivity
import com.viksaa.sssplash.lib.cnst.Flags
import com.viksaa.sssplash.lib.model.ConfigSplash


class SplashActivity : AwesomeSplash() {

    override fun initSplash(configSplash: ConfigSplash) {

       //Customize Circular Reveal
        configSplash.backgroundColor = R.color.colorPrimaryDark //any color you want form colors.xml
        configSplash.animCircularRevealDuration = 2000 //int ms
        configSplash.revealFlagX = Flags.REVEAL_RIGHT  //or Flags.REVEAL_LEFT
        configSplash.revealFlagY = Flags.REVEAL_BOTTOM //or Flags.REVEAL_TOP

        //Customize Logo
        configSplash.logoSplash = R.drawable.movie //or any other drawable
        configSplash.animLogoSplashDuration = 2000 //int ms
        configSplash.animLogoSplashTechnique =
            Techniques.FadeIn //choose one form Techniques

        //Customize Title
        configSplash.titleSplash = "Papayin"
        configSplash.titleTextColor = R.color.colorWhite
        configSplash.titleTextSize = 60f //float value
        configSplash.animTitleDuration = 3000
        configSplash.animTitleTechnique = Techniques.FlipInX
        configSplash.titleFont =
            "fonts/Pacifico.ttf" //provide string to your font located in assets/fonts/

    }

    override fun animationsFinished() {
        val mainIntent = Intent(this@SplashActivity, BaseActivity::class.java)
        startActivity(mainIntent)
        finish()
    }
}
