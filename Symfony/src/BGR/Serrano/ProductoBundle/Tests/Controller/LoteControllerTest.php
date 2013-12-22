<?php

namespace BGR\Serrano\ProductoBundle\Tests\Controller;

use Symfony\Bundle\FrameworkBundle\Test\WebTestCase;

class LoteControllerTest extends WebTestCase
{
    public function testGetall()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/lote/getAll');
    }

    public function testSave()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/lote/save');
    }

    public function testUpdate()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/lote/update');
    }

    public function testDelete()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/lote/delete');
    }

}
