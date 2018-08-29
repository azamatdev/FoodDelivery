package uz.androidmk.fooddelivery.di.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import uz.androidmk.fooddelivery.MvpApp;
import uz.androidmk.fooddelivery.di.ApplicationContext;
import uz.androidmk.fooddelivery.di.module.ApplicationModule;

/**
 * Created by Azamat on 8/29/2018.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(MvpApp mvpApp);

    @ApplicationContext
    Context context();

    Application application();

}
