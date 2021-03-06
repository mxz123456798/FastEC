package me.maxandroid.fastec;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import me.maxandroid.core.app.Latte;
import me.maxandroid.core.delegates.web.event.TestEvent;
import me.maxandroid.core.net.interceptor.DebugInterceptor;
import me.maxandroid.ec.database.DatabaseManager;
import me.maxandroid.ec.icon.FontEcModule;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withLoaderDelayed(1000)
                .withJavascriptInterface("latte")
                .withWebEvent("test", new TestEvent())
                .withApiHost("http://www.wanandroid.com/tools/mockapi/7563/")
                .withInterceptor(new DebugInterceptor("test", R.raw.test))
                .configure();

        DatabaseManager.getInstance().init(this);
        initStetho();
    }

    private void initStetho() {
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build());
    }
}
