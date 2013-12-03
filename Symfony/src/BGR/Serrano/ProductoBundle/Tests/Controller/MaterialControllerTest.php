<?php

namespace BGR\Serrano\ProductoBundle\Tests\Controller;

use Symfony\Bundle\FrameworkBundle\Test\WebTestCase;

class MaterialControllerTest extends WebTestCase
{
    public function testGetall()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/material/getAll');
    }

    public function testDelete()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/material/delete');
    }

    public function testUpdate()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/material/update');
    }

    public function testSave()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/material/save');
    }

}
