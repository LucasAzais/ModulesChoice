import org.springframework.web.servlet.i18n.FixedLocaleResolver
beans = {
	localeResolver(FixedLocaleResolver, Locale.ENGLISH) {
		Locale.setDefault(Locale.ENGLISH)
	}
}