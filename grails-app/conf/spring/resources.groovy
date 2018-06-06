import org.springframework.web.servlet.i18n.SessionLocaleResolver
import com.alla.sharai.UserPasswordEncoderListener

// Place your Spring DSL code here
beans = {
    userPasswordEncoderListener(UserPasswordEncoderListener)
}
beans = {
    localeResolver(SessionLocaleResolver) {
        defaultLocale= new java.util.Locale('en')
    }
}
