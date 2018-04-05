package com.objectteam.willynilly

import com.objectteam.willynilly.gameObjects.Fan

class FanTest extends GroovyTestCase {
    void setUp() {
        super.setUp()
    }

    void testUpdate() {
        var fan = new Fan();
        fan.update(1000);
        assertTrue("Update method doesn't fail", true);
    }
}
