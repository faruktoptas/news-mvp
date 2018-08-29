package me.toptas.rssreader

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(MainPresenterTest::class, RssPresenterTest::class)
class UnitTestSuite