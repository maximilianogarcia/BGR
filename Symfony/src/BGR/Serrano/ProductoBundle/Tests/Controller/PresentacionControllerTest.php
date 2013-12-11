<?php

namespace BGR\Serrano\ProductoBundle\Tests\Controller;

use Symfony\Bundle\FrameworkBundle\Test\WebTestCase;

class PresentacionControllerTest extends WebTestCase
{
    public function testSave()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/presentacion/save');
    }

    public function testUpdate()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/presentacion/update');
    }

    public function testGetall()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/presentacion/getAll');
    }

    public function testDelete()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/presentacion/delete');
    }

}
